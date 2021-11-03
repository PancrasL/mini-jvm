package indi.pancras.jvm.classfile.pool;

import indi.pancras.jvm.classfile.ClassReader;
import indi.pancras.jvm.classfile.pool.poolinfo.*;


public abstract class BaseConstantInfo {
    public final int tag;

    public BaseConstantInfo(int tag) {
        this.tag = tag;
    }

    public static BaseConstantInfo readConstantInfo(ClassReader reader) {
        byte tag = reader.readByte();
        switch (tag) {
            case ConstantPoolTag.CONSTANT_TAG_UTF8: {
                short length = reader.readShort();
                byte[] bytes = reader.readBytes(length);
                return new Utf8Info(tag, bytes);
            }
            case ConstantPoolTag.CONSTANT_TAG_INTEGER: {
                int value = reader.readInt();
                return new IntegerInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_FLOAT: {
                float value = reader.readFloat();
                return new FloatInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_LONG: {
                long value = reader.readLong();
                return new LongInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_DOUBLE: {
                double value = reader.readDouble();
                return new DoubleInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_CLASS: {
                short value = reader.readShort();
                return new ClassInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_STRING: {
                short value = reader.readShort();
                return new StringInfo(tag, value);
            }
            case ConstantPoolTag.CONSTANT_TAG_FIELDREF: {
                short classIndex = reader.readShort();
                short nameAndTypeIndex = reader.readShort();
                return new FieldRefInfo(tag, classIndex, nameAndTypeIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_METHODREF: {
                short classIndex = reader.readShort();
                short nameAndTypeIndex = reader.readShort();
                return new MethodRefInfo(tag, classIndex, nameAndTypeIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_INTERFACEMETHODREF: {
                short classIndex = reader.readShort();
                short nameAndTypeIndex = reader.readShort();
                return new InterfaceMethodRefInfo(tag, classIndex, nameAndTypeIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_NAMEANDTYPE: {
                short nameIndex = reader.readShort();
                short descriptorIndex = reader.readShort();
                return new NameAndTypeInfo(tag, nameIndex, descriptorIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_METHODHANDLE: {
                byte referenceKind = reader.readByte();
                short referenceIndex = reader.readByte();
                return new MethodRefInfo(tag, referenceKind, referenceIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_METHODTYPE: {
                short descriptorIndex = reader.readShort();
                return new MethodTypeInfo(tag, descriptorIndex);
            }
            case ConstantPoolTag.CONSTANT_TAG_INVOKEDYNAMIC: {
                short bootstrapMethodAttrIndex = reader.readShort();
                short nameAndTypeIndex = reader.readShort();
                return new InvokeDynamicInfo(tag, bootstrapMethodAttrIndex, nameAndTypeIndex);
            }
            default:
                break;
        }
        throw new ClassFormatError("Constant pool tag error!");
    }
}