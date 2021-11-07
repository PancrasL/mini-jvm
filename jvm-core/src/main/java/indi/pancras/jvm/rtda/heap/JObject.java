package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.SlotsUtil;

public class JObject {
    private final JClass clazz;
    private final Slot[] fields;

    public JObject(JClass clazz, int slotCount) {
        this.clazz = clazz;
        this.fields = new Slot[slotCount];
    }

    /**
     * 判断当前类是否是另一个类或接口的实例
     *
     * @param clazz the target class
     * @return true or false
     */
    public boolean isInstanceOf(JClass clazz) {
        return this.clazz.isAssignableFrom(clazz);
    }

    public void setInt(int slotId, int val) {
        SlotsUtil.setInt(fields, slotId, val);
    }

    public int getInt(int slotId) {
        return SlotsUtil.getInt(fields, slotId);
    }

    public void setFloat(int slotId, float val) {
        SlotsUtil.setFloat(fields, slotId, val);
    }

    public float getFloat(int slotId) {
        return SlotsUtil.getFloat(fields, slotId);
    }

    public void setLong(int slotId, long val) {
        SlotsUtil.setLong(fields, slotId, val);
    }

    public long getLong(int slotId) {
        return SlotsUtil.getLong(fields, slotId);
    }

    public void setDouble(int slotId, double val) {
        SlotsUtil.setDouble(fields, slotId, val);
    }

    public double getDouble(int slotId) {
        return SlotsUtil.getDouble(fields, slotId);
    }

    public void setRef(int slotId, Reference ref) {
        SlotsUtil.setRef(fields, slotId, ref);
    }

    public Reference getRef(int slotId) {
        return SlotsUtil.getRef(fields, slotId);
    }
}
