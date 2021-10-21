package indi.pancras.jvm.rtda;

/**
 * @author PancrasL
 */
public class OperandStack {
    private final Slot[] slots;
    private int top;

    public OperandStack(int maxStack) {
        slots = new Slot[maxStack];
        top = 0;
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public void pushInt(int value) {
        slots[top++].val = value;
    }

    public int popInt() {
        return slots[--top].val;
    }

    public void pushFloat(float value) {
        int bits = Float.floatToIntBits(value);
        pushInt(bits);
    }

    public float popFloat() {
        int bits = popInt();
        return Float.intBitsToFloat(bits);
    }

    public void pushLong(long val) {
        // 采用大端存储，高字节存放低地址处
        // high
        slots[top++].val = (int) (val >> 32);
        // low
        slots[top++].val = (int) (val);
    }

    public long popLong() {
        int low = slots[--top].val;
        int high = slots[--top].val;
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public void pushDouble(double val) {
        long bits = Double.doubleToLongBits(val);
        pushLong(bits);
    }

    public double popDouble() {
        long bits = popLong();
        return Double.longBitsToDouble(bits);
    }

    public void pushRef(Reference ref) {
        slots[top++].ref = ref;
    }

    public Reference popRef() {
        Reference ref = slots[top].ref;
        slots[top].ref = null;
        return ref;
    }
}
