package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;

import indi.pancras.jvm.rtda.DescriptorFlag;
import lombok.ToString;

@ToString
public class MethodDescriptor {
    private String[] paramTypes;
    private String returnType;

    private String descriptor;
    private int offset;

    public MethodDescriptor(String descriptor) {
        this.descriptor = descriptor;
        this.offset = 0;
        parse();
    }

    public String[] getParamTypes() {
        return paramTypes;
    }

    public String getReturnType() {
        return returnType;
    }

    private void parse() {
        startParams();
        parseParamTypes();
        endParams();
        parseReturnType();
        finish();
    }

    private void startParams() {
        if (readChar() != '(') {
            parseFail();
        }
    }

    private void parseParamTypes() {
        ArrayList<String> types = new ArrayList<>();
        while (true) {
            String t = parseFieldType();
            if (t.isEmpty()) {
                break;
            }
            types.add(t);
        }
        paramTypes = new String[types.size()];
        paramTypes = types.toArray(paramTypes);
    }

    private void endParams() {
        if (readChar() != ')') {
            parseFail();
        }
    }

    private void parseReturnType() {
        if (readChar() == DescriptorFlag.VOID_FLAG) {
            returnType = "V";
            return;
        }

        unreadChar();
        String t = parseFieldType();
        if (!t.isEmpty()) {
            returnType = t;
            return;
        }
        parseFail();
    }

    private void finish() {
        if (offset != descriptor.length()) {
            parseFail();
        }
    }

    private String parseFieldType() {
        switch (readChar()) {
            case DescriptorFlag.BOOLEAN_FLAG:
                return "Z";
            case DescriptorFlag.BYTE_FLAG:
                return "B";
            case DescriptorFlag.SHORT_FLAG:
                return "S";
            case DescriptorFlag.CHAR_FLAG:
                return "C";
            case DescriptorFlag.INT_FLAG:
                return "I";
            case DescriptorFlag.FLOAT_FLAG:
                return "F";
            case DescriptorFlag.DOUBLE_FLAG:
                return "D";
            case DescriptorFlag.LONG_FLAG:
                return "J";
            case DescriptorFlag.OBJECT_FLAG:
                unreadChar();
                return parseObjectType();
            case DescriptorFlag.ARRAY_FLAG:
                unreadChar();
                return parseArrayType();
            default:
                unreadChar();
                return "";
        }
    }

    private String parseObjectType() {
        int end = descriptor.indexOf(';', offset);
        String objType = descriptor.substring(offset, end);
        offset = end + 1;
        return objType;
    }

    private String parseArrayType() {
        int arrStart = offset;
        offset++;
        parseFieldType();
        int arrEnd = offset;
        return descriptor.substring(arrStart, arrEnd);
    }

    private char readChar() {
        return descriptor.charAt(offset++);
    }

    private void unreadChar() {
        offset--;
    }

    private void parseFail() {
        throw new IllegalArgumentException("Parse descriptor fail: " + descriptor);
    }

}
