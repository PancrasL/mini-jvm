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

/**
 * @author PancrasL
 */
public abstract class AbstractAttribute {
    public static AbstractAttribute readAttribute(ClassReader reader) {
        short attrNameIndex = reader.readShort();
        int attrLength = reader.readInt();
        ConstantPool pool = reader.getPool();
        String attrName = pool.getUtf8(attrNameIndex);

        switch (attrName) {
            case AttributeType.CODE:
                return new CodeAttr();
            case AttributeType.CONSTANT_VALUE:
                return new ConstantValueAttr();
            case AttributeType.EXCEPTIONS:
                return new ExceptionAttr();
            case AttributeType.LINE_NUMBER_TABLE:
                return new LineNumberTableAttr();
            case AttributeType.LOCAL_VARIABLE_TABLE:
                return new LocalVariableTableAttr();
            case AttributeType.SOURCE_FILE:
                return new SourceFileAttr();
            case AttributeType.SYNTHETIC:
                return new SyntheticAttr();
            case AttributeType.DEPRECATED:
                return new DeprecatedAttr();
            //不支持的属性
            default:
                return new UnparsedAttr();
        }
    }
}
