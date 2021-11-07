package indi.pancras.jvm.rtda.stack;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.SlotsUtil;

public class LocalVars {
    private final Slot[] slots;

    public LocalVars(int maxLocals) {
        slots = new Slot[maxLocals];
    }

    public void setInt(int index, int val) {
        SlotsUtil.setInt(slots, index, val);
    }

    public int getInt(int index) {
        return SlotsUtil.getInt(slots, index);
    }

    public void setFloat(int index, float val) {
        SlotsUtil.setFloat(slots, index, val);
    }

    public float getFloat(int index) {
        return SlotsUtil.getFloat(slots, index);
    }

    public void setLong(int index, long val) {
        SlotsUtil.setLong(slots, index, val);
    }

    public long getLong(int index) {
        return SlotsUtil.getLong(slots, index);
    }

    public void setDouble(int index, double val) {
        SlotsUtil.setDouble(slots, index, val);
    }

    public double getDouble(int index) {
        return SlotsUtil.getDouble(slots, index);
    }

    public void setRef(int index, Reference ref) {
        SlotsUtil.setRef(slots, index, ref);
    }

    public Reference getRef(int index) {
        return SlotsUtil.getRef(slots, index);
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
