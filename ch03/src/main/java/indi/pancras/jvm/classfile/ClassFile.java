package indi.pancras.jvm.classfile;

import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;

/**
 * @author PancrasL
 */
@Getter
public class ClassFile {
    private static final int MAGIC_NUM = 0xCAFEBABE;
    private ClassReader reader;

    private int magic;
    private short minorVersion;
    private short majorVersion;
    private ConstantPool constantPool;
    private short accessFlag;
    private short thisClassIndex;
    private short superClassIndex;
    private short[] interfaceIndexes;
    private FieldInfo[] fields;
    private MethodInfo[] methods;
    private BaseAttr[] attributes;

    public ClassFile(byte[] classData) {
        reader = new ClassReader(classData);

        readAndCheckMagic();
        readAndCheckVersion();
        readConstantPool();
        readAccessFlag();
        readClassNameIndex();
        readSuperClassNameIndex();
        readInterfaceIndexes();
        readFields();
        readMethods();
        readAttributes();
    }

    private void readAndCheckMagic() {
        magic = reader.readInt();
        if (magic != MAGIC_NUM) {
            throw new ClassFormatError("Magic num error!");
        }
    }

    private void readAndCheckVersion() {
        minorVersion = reader.readShort();
        majorVersion = reader.readShort();

        switch (majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (minorVersion == 0) {
                    return;
                }
            default:
                break;
        }
        throw new UnsupportedClassVersionError();
    }

    private void readConstantPool() {
        constantPool = ConstantPool.readConstantPool(reader);
    }

    private void readAccessFlag() {
        accessFlag = reader.readShort();
    }

    private void readClassNameIndex() {
        thisClassIndex = reader.readShort();
    }

    private void readSuperClassNameIndex() {
        superClassIndex = reader.readShort();
    }

    private void readInterfaceIndexes() {
        int cnt = reader.readShort();
        interfaceIndexes = new short[cnt];
        for (int i = 0; i < cnt; i++) {
            interfaceIndexes[i] = reader.readShort();
        }
    }

    private void readFields() {
        fields = FieldInfo.readFields(reader, constantPool);
    }

    private void readMethods() {
        methods = MethodInfo.readMethods(reader, constantPool);
    }

    private void readAttributes() {
        attributes = BaseAttr.readAttributes(reader, constantPool);
    }
}
