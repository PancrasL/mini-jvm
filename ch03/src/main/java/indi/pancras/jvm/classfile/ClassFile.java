package indi.pancras.jvm.classfile;

import indi.pancras.jvm.classfile.attribute.AbstractAttribute;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;

/**
 * @author PancrasL
 */
public class ClassFile {
    private ClassReader classReader;

    private int magic;
    private short minorVersion;
    private short majorVersion;
    private ConstantPool pool;
    private short accessFlag;
    private short thisClassIndex;
    private short superClassIndex;
    private short interfacesCount;
    private short[] interfaceIndexes;
    private short fieldCount;
    private FieldInfo[] fields;
    private short methodCount;
    private MethodInfo[] methods;
    private short attributeCount;
    private AbstractAttribute[] attributes;

    public ClassFile(byte[] classData) {
        classReader = new ClassReader(classData);

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
        magic = classReader.readInt();
        if (magic != 0xCAFEBABE) {
            throw new ClassFormatError("magic num error!");
        }
    }

    private void readAndCheckVersion() {
        minorVersion = classReader.readShort();
        majorVersion = classReader.readShort();

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
        pool = ConstantPool.readConstantPool(classReader);
        classReader.setPool(pool);
    }

    private void readAccessFlag() {
        accessFlag = classReader.readShort();
    }

    private void readClassNameIndex() {
        thisClassIndex = classReader.readShort();
    }

    private void readSuperClassNameIndex() {
        superClassIndex = classReader.readShort();
    }

    private void readInterfaceIndexes() {
        interfacesCount = classReader.readShort();
        short cnt = interfacesCount;
        interfaceIndexes = new short[cnt];
        for (int i = 0; i < cnt; i++) {
            interfaceIndexes[i] = classReader.readShort();
        }
    }

    private void readFields() {
        fieldCount = classReader.readShort();
        fields = new FieldInfo[fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            fields[i] = FieldInfo.readField(classReader);
        }
    }

    private void readMethods() {
        methodCount = classReader.readShort();
        methods = new MethodInfo[methodCount];
        for (int i = 0; i < methodCount; i++) {
            methods[i] = MethodInfo.readMethod(classReader);
        }
    }

    private void readAttributes() {
        attributeCount = classReader.readShort();
        attributes = new AbstractAttribute[attributeCount];
        for (int i = 0; i < attributeCount; i++) {
            attributes[i] = AbstractAttribute.readAttribute(classReader);
        }
    }
}
