package indi.pancras.jvm.rtda.heap;

import java.util.HashMap;
import java.util.Map;

public class JClassNameHelper {
    public static final Map<String, String> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    public static String getArrayClassName(String className) {
        String descriptor;
        // 如果是数组类名，描述符就是类名，直接返回
        if (className.charAt(0) == '[') {
            descriptor = className;
        }
        // 如果是基本类型名，返回对应的类型描述符
        else if (primitiveTypes.containsKey(className)) {
            descriptor = primitiveTypes.get(className);
        }
        // 普通类名，加上分号构成类型描述符
        else {
            descriptor = "L" + className + ";";
        }
        return "[" + descriptor;
    }

    public static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array: " + className);
    }

    private static String toClassName(String descriptor) {
        // array，如果以[开头，肯定是数组，描述符即是类名
        if (descriptor.charAt(0) == '[') {
            return descriptor;
        }
        // object，如果以L开头，肯定是类型描述符，去掉开头的L和结尾的分号
        if (descriptor.charAt(0) == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }
        // primitive
        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(descriptor)) {
                return entry.getKey();
            }
        }
        throw new RuntimeException("Invalid descriptor: " + descriptor);
    }
}
