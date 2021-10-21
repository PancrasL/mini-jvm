package indi.pancras.jvm.rtda;

/**
 * @author PancrasL
 */
public class LocalVars {
    private final Slot[] slots;

    public LocalVars(int maxLocals) {
        slots = new Slot[maxLocals];
    }

    public void setInt(int index, int val) {
        slots[index] = new Slot(val);
    }

    public int getInt(int index) {
        int val = slots[index].val;
        slots[index] = null;
        return val;
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
        int high = slots[index].val;
        int low = slots[index + 1].val;
        slots[index] = null;
        slots[index + 1] = null;
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
        Reference ref = slots[index].ref;
        slots[index] = null;
        return ref;
    }
}
