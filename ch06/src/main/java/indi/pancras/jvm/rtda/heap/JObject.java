package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.base.Slot;

public class JObject {
    private final JClass clazz;
    private final List<Slot> fields;

    public JObject(JClass clazz, int slotsCnt) {
        this.clazz = clazz;
        this.fields = new ArrayList<>(slotsCnt);
    }

    public void setInt(int slotId, int val) {
        fields.set(slotId, new Slot(val));
    }

    public int getInt(int slotId) {
        return fields.get(slotId).getVal();
    }

    public void setFloat(int slotId, float val) {
        setInt(slotId, Float.floatToIntBits(val));
    }

    public float getFloat(int slotId) {
        int val = fields.get(slotId).getVal();
        return Float.intBitsToFloat(val);
    }

    public void setLong(int slotId, long val) {
        int low = (int) (val);
        int high = (int) (val >> 32);
        setInt(slotId, high);
        setInt(slotId + 1, low);
    }

    public long getLong(int slotId) {
        int high = fields.get(slotId).getVal();
        int low = fields.get(slotId + 1).getVal();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public void setDouble(int slotId, double val) {
        setLong(slotId, Double.doubleToLongBits(val));
    }

    public double getDouble(int slotId) {
        long val = getLong(slotId);
        return Double.longBitsToDouble(val);
    }

    public void setRef(int slotId, Reference ref) {
        fields.set(slotId, new Slot(ref));
    }

    public Reference getRef(int slotId) {
        return fields.get(slotId).getRef();
    }
}
