package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.SlotsUtil;
import lombok.Getter;

@Getter
public class JObject {
    // 标识是否为数组对象
    private final boolean isArrObj;

    private final JClass clazz;
    // 属性值
    private final Slot[] slots;
    // 数组对象该字段有效
    private final Object data;
    private final int arrayLength;

    public JObject(JClass clazz) {
        this.clazz = clazz;
        this.slots = new Slot[clazz.getInstanceSlotCount()];

        this.isArrObj = false;
        this.data = null;
        this.arrayLength = -1;
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

    /**
     * 数组对象的相关方法
     */
    public JObject(JClass clazz, int arrayLength, Object data) {
        this.isArrObj = true;
        this.clazz = clazz;
        this.arrayLength = arrayLength;
        this.data = data;
        this.slots = null;
    }

    public byte[] bytes() {
        return (byte[]) data;
    }

    public short[] shorts() {
        return (short[]) data;
    }

    public int[] ints() {
        return (int[]) data;
    }

    public long[] longs() {
        return (long[]) data;
    }

    public char[] chars() {
        return (char[]) data;
    }

    public float[] floats() {
        return (float[]) data;
    }

    public double[] doubles() {
        return (double[]) data;
    }

    public Reference[] refs() {
        return (Reference[]) data;
    }

    public int getArrayLength() {
        if (!isArrObj) {
            throw new RuntimeException("Obj is not array.");
        }
        return arrayLength;
    }

    // reflection
    public Reference getRefVar(String name, String descriptor) {
        Field field = clazz.getField(name, descriptor, false);
        return SlotsUtil.getRef(slots, field.getSlotId());
    }

    public void setRefVar(String name, String descriptor, Reference ref) {
        Field field = clazz.getField(name, descriptor, false);
        SlotsUtil.setRef(slots, field.getSlotId(), ref);
    }
}
