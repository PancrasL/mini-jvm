package indi.pancras.jvm.utils;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;

public class SlotsUtil {

    public static void setInt(Slot[] slots, int index, int val) {
        slots[index] = new Slot(val);
    }

    public static int getInt(Slot[] slots, int index) {
        return slots[index].getVal();
    }

    public static void setFloat(Slot[] slots, int index, float val) {
        slots[index] = new Slot(Float.floatToIntBits(val));
    }

    public static float getFloat(Slot[] slots, int index) {
        int val = slots[index].getVal();
        return Float.intBitsToFloat(val);
    }

    public static void setLong(Slot[] slots, int index, long val) {
        int low = (int) (val);
        int high = (int) (val >> 32);
        slots[index] = new Slot(high);
        slots[index + 1] = new Slot(low);
    }

    public static long getLong(Slot[] slots, int index) {
        int high = slots[index].getVal();
        int low = slots[index + 1].getVal();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public static void setDouble(Slot[] slots, int index, double val) {
        long l = Double.doubleToLongBits(val);
        int low = (int) (l);
        int high = (int) (l >> 32);
        slots[index] = new Slot(high);
        slots[index + 1] = new Slot(low);
    }

    public static double getDouble(Slot[] slots, int index) {
        int high = slots[index].getVal();
        int low = slots[index + 1].getVal();
        long l = (((long) high) << 32) | ((long) low & 0x0ffffffffL);
        return Double.longBitsToDouble(l);
    }

    public static void setRef(Slot[] slots, int index, Reference ref) {
        slots[index] = new Slot(ref);
    }

    public static Reference getRef(Slot[] slots, int index) {
        return slots[index].getRef();
    }

    public static void setSlot(Slot[] slots, int index, Slot slot) {
        slots[index] = slot;
    }
}
