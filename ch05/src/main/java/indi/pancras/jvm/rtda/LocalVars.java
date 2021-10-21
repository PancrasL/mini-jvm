package indi.pancras.jvm.rtda;

/**
 * @author PancrasL
 */
public class LocalVars {
    private Slot[] slots;

    private int top;

    public LocalVars(int maxLocals) {
        slots = new Slot[maxLocals];
        for (int i = 0; i < maxLocals; i++) {
            slots[i] = new Slot();
        }
    }

    public void setInt(int index, int val) {
        slots[index].val = val;
    }

    public int getInt(int index) {
        return slots[index].val;
    }

    public void setFloat(int index, float val) {
        slots[index].val = Float.floatToIntBits(val);
    }

    public float getFloat(int index) {
        int bits = slots[index].val;
        return Float.intBitsToFloat(bits);
    }

    public void setLong(int index, long val) {
        slots[index].val = (int) (val >> 32);
        slots[index + 1].val = (int) (val);
    }

    public long getLong(int index) {
        int high = slots[index].val;
        int low = slots[index + 1].val;

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
        slots[index].ref = ref;
    }

    public Reference getRef(int index) {
        return slots[index].ref;
    }
}
