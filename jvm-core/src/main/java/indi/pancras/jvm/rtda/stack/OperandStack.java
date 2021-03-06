package indi.pancras.jvm.rtda.stack;

import java.util.Arrays;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;

public class OperandStack {
    private final Slot[] slots;
    private int top;

    public OperandStack(int maxStack) {
        slots = new Slot[maxStack];
        top = 0;
    }

    /**
     * 返回距离操作数栈顶开始的第count个引用（0返回栈顶元素，1返回栈顶下面的元素）
     *
     * @param count count
     * @return reference
     */
    public Reference getRefFromTop(int count) {
        return slots[top - count - 1].getRef();
    }

    public void pushInt(int value) {
        slots[top++] = new Slot(value);
    }

    public int popInt() {
        int val = slots[--top].getVal();
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
        int low = slots[top - 1].getVal();
        int high = slots[top - 2].getVal();
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
        Reference ref = slots[--top].getRef();
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
        return "OperandStack{" +
                "slots=" + Arrays.toString(slots) +
                ", top=" + top +
                '}';
    }
}
