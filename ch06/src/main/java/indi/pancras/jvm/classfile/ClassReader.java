package indi.pancras.jvm.classfile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import indi.pancras.jvm.classfile.attribute.BaseAttr;
import indi.pancras.jvm.classfile.field.FieldInfo;
import indi.pancras.jvm.classfile.method.MethodInfo;
import indi.pancras.jvm.classfile.pool.ConstantPool;


public class ClassReader {
    private final byte[] data;
    private int index;

    public ClassReader(byte[] data) {
        this.data = data;
        this.index = 0;
    }

    public ClassFile parseClassFile() {
        ClassFile classFile = new ClassFile();
        parseMagic(classFile);
        parseVersion(classFile);
        parseConstantPool(classFile);
        parseAccessFlag(classFile);
        parseThisClassNameIndex(classFile);
        parseSuperClassNameIndex(classFile);
        parseInterfaceIndexes(classFile);
        parseFields(classFile);
        parseMethods(classFile);
        parseAttributes(classFile);

        return classFile;
    }


    private void parseMagic(ClassFile classFile) {
        int magic = readInt();
        if (magic != ClassFile.MAGIC_NUM) {
            throw new ClassFormatError("Magic num error!");
        }
        classFile.setMagic(magic);
    }

    private void parseVersion(ClassFile classFile) {
        short minorVersion = readShort();
        short majorVersion = readShort();

        switch (majorVersion) {
            case 45:
                classFile.setMinorVersion(minorVersion);
                classFile.setMajorVersion(majorVersion);
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (minorVersion == 0) {
                    classFile.setMinorVersion(minorVersion);
                    classFile.setMajorVersion(majorVersion);
                    return;
                }
            default:
                break;
        }
        throw new UnsupportedClassVersionError();
    }

    private void parseConstantPool(ClassFile classFile) {
        ConstantPool constantPool = ConstantPool.readConstantPool(this);
        classFile.setConstantPool(constantPool);
    }

    private void parseAccessFlag(ClassFile classFile) {
        short accessFlag = readShort();
        classFile.setAccessFlag(accessFlag);
    }

    private void parseThisClassNameIndex(ClassFile classFile) {
        short index = readShort();
        classFile.setThisClassIndex(index);
    }

    private void parseSuperClassNameIndex(ClassFile classFile) {
        short index = readShort();
        classFile.setSuperClassIndex(index);
    }

    private void parseInterfaceIndexes(ClassFile classFile) {
        short cnt = readShort();
        int[] interfaceIndexes = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            interfaceIndexes[i] = readShort();
        }
        classFile.setInterfaceCount(cnt);
        classFile.setInterfaceIndexes(interfaceIndexes);
    }

    private void parseFields(ClassFile classFile) {
        FieldInfo[] fieldInfos = FieldInfo.readFields(this, classFile.getConstantPool());
        classFile.setFieldCount(fieldInfos.length);
        classFile.setFields(fieldInfos);
    }

    private void parseMethods(ClassFile classFile) {
        MethodInfo[] methods = MethodInfo.readMethods(this, classFile.getConstantPool());
        classFile.setMethodCount(methods.length);
        classFile.setMethods(methods);
    }

    private void parseAttributes(ClassFile classFile) {
        BaseAttr[] attrs = BaseAttr.readAttributes(this, classFile.getConstantPool());
        classFile.setAttributes(attrs);
    }

    public byte readByte() {
        return read(1)[0];
    }

    public short readShort() {
        byte[] bytes = read(2);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getShort();
    }

    public int readInt() {
        byte[] bytes = read(4);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    public float readFloat() {
        byte[] bytes = read(4);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    public long readLong() {
        byte[] bytes = readBytes(8);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getLong();
    }

    public double readDouble() {
        byte[] bytes = readBytes(8);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getDouble();
    }

    public byte[] readBytes(int length) {
        return read(length);
    }

    private byte[] read(int length) {
        byte[] bytes = Arrays.copyOfRange(data, index, index + length);
        index += length;
        return bytes;
    }
}
