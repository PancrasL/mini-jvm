package indi.pancras.jvm.classfile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import indi.pancras.jvm.classfile.pool.ConstantPool;
import lombok.Getter;
import lombok.Setter;

/**
 * @author PancrasL
 */
public class ClassReader {
    private final byte[] data;
    private int index;

    /**
     * 在解析完常量池后置入，供其他结构根据索引值查询具体的常量
     */
    @Getter
    @Setter
    private ConstantPool pool;

    public ClassReader(byte[] data) {
        this.data = data;
        this.index = 0;
    }

    public byte readByte() {
        return read(1)[0];
    }

    public short readShort() {
        byte[] bytes = read(2);
        return (short) (bytes[0] | bytes[1] << 8);
    }

    public int readInt() {
        byte[] bytes = read(4);
        return bytes[0] | bytes[1] << 8 | bytes[2] << 16 | bytes[3] << 24;
    }

    public float readFloat() {
        int intBits = readInt();
        return Float.intBitsToFloat(intBits);
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
