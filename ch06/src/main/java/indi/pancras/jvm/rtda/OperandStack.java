package indi.pancras.jvm.rtda;

public class OperandStack {
    private final Slot[] slots;
    private int top;

    public OperandStack(int maxStack) {
        slots = new Slot[maxStack];
        top = 0;
    }

    public void pushInt(int value) {
        slots[top++] = new Slot(value);
    }

    public int popInt() {
        int val = slots[--top].val;
        slots[top] = null;
        return val;
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
        slots[top++] = new Slot((int) (val >> 32));
        // low
        slots[top++] = new Slot((int) (val));
    }

    public long popLong() {
        int low = slots[top - 1].val;
        int high = slots[top - 2].val;
        slots[top - 1] = null;
        slots[top - 2] = null;
        top -= 2;
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
        slots[top++] = new Slot(ref);
    }

    public Reference popRef() {
        Reference ref = slots[--top].ref;
        slots[top] = null;
        return ref;
    }

    public void pushSlot(Slot slot) {
        slots[top++] = slot;
    }

    public Slot popSlot() {
        Slot slot = slots[--top];
        slots[top] = null;
        return slot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < top; i++) {
            sb.append(slots[i]);
            if (i != top - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');

        return sb.toString();
    }
}
