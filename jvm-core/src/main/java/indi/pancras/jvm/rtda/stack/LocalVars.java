package indi.pancras.jvm.rtda.stack;

import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.base.Slot;

public class LocalVars {
    private final Slot[] slots;

    public LocalVars(int maxLocals) {
        slots = new Slot[maxLocals];
    }

    public void setInt(int index, int val) {
        slots[index] = new Slot(val);
    }

    public int getInt(int index) {
        return slots[index].getVal();
    }

    public void setFloat(int index, float val) {
        setInt(index, Float.floatToIntBits(val));
    }

    public float getFloat(int index) {
        int bits = getInt(index);
        return Float.intBitsToFloat(bits);
    }

    public void setLong(int index, long val) {
        slots[index] = new Slot((int) (val >> 32));
        slots[index + 1] = new Slot((int) (val));
    }

    public long getLong(int index) {
        int high = slots[index].getVal();
        int low = slots[index + 1].getVal();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public void setDouble(int index, double val) {
        long bits = Double.doubleToLongBits(val);
        setLong(index, bits);
    }

    public double getDouble(int index) {
        long bits = getLong(index);
        return Double.longBitsToDouble(bits);
    }

    public void setRef(int index, Reference ref) {
        slots[index] = new Slot(ref);
    }

    public Reference getRef(int index) {
        return slots[index].getRef();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        // Slot[]结构
        sb.append("  Slot:\n");
        for (int i = 0; i < slots.length; i++) {
            String s = slots[i] == null ? "null" : slots[i].toString();
            sb.append(String.format("    %2d, %5s\n", i, s));
        }
        return sb.toString();
    }
}
