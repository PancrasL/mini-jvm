package indi.pancras.jvm.classfile.attribute;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.attribute.attrinfo.CodeAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.ConstantValueAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.DeprecatedAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.ExceptionAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.LineNumberTableAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.LocalVariableTableAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.SourceFileAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.SyntheticAttr;
import indi.pancras.jvm.classfile.attribute.attrinfo.UnparsedAttr;
import indi.pancras.jvm.classfile.pool.ConstantPool;


public abstract class BaseAttr {
    public static BaseAttr[] readAttributes(ClassReader reader, ConstantPool pool) {
        short cnt = reader.readShort();
        BaseAttr[] attributes = new BaseAttr[cnt];
        for (int i = 0; i < cnt; i++) {
            attributes[i] = readAttribute(reader, pool);
        }
        return attributes;
    }

    private static BaseAttr readAttribute(ClassReader reader, ConstantPool pool) {
        short attrNameIndex = reader.readShort();
        int attrLength = reader.readInt();
        String attrName = pool.getUTF8(attrNameIndex);

        switch (attrName) {
            case AttributeType.CODE:
                return new CodeAttr(reader, pool);
            case AttributeType.CONSTANT_VALUE:
                return new ConstantValueAttr(reader);
            case AttributeType.EXCEPTIONS:
                return new ExceptionAttr(reader);
            case AttributeType.LINE_NUMBER_TABLE:
                return new LineNumberTableAttr(reader);
            case AttributeType.LOCAL_VARIABLE_TABLE:
                return new LocalVariableTableAttr(reader);
            case AttributeType.SOURCE_FILE:
                return new SourceFileAttr(reader);
            case AttributeType.SYNTHETIC:
                return new SyntheticAttr(reader);
            case AttributeType.DEPRECATED:
                return new DeprecatedAttr(reader);
            //不支持的属性
            default:
                return new UnparsedAttr(attrName, attrLength, reader);
        }
    }
}
