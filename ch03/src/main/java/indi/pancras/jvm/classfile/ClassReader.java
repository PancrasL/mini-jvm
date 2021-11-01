package indi.pancras.jvm.classfile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;


public class ClassReader {
    private final byte[] data;
    private int index;

    public ClassReader(byte[] data) {
        this.data = data;
        this.index = 0;
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
