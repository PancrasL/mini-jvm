package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.utils.SlotsUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
public class JObject {
    /**
     * 普通对象
     */
    // 类指针
    private final JClass clazz;
    // 属性值
    private final Slot[] slots;
    /**
     * 数组对象
     */
    private final Object data;
    private final ArrayType type;
    private final int arrayLength;

    /**
     * JObject实例的额外信息
     */
    // 当JObject是类对象时，extra指向该类JClass。
    // 例如，java.lang.Object的类对象，clazz指向java.lang.Class，extra指向java.lang.Object
    @Setter
    private JClass extra;

    public JObject(JClass clazz) {
        this.clazz = clazz;
        this.slots = new Slot[clazz.getInstanceSlotCount()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }

        this.type = ArrayType.NONE_ARRAY;
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
    public JObject(JClass clazz, int arrayLength, ArrayType type, Object data) {
        this.type = type;
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
        if (type == ArrayType.NONE_ARRAY) {
            throw new RuntimeException("Obj is not array.");
        }
        return arrayLength;
    }

    // reflection
    public Reference getFieldValue(String name, String descriptor) {
        Field field = clazz.getField(name, descriptor, false);
        return SlotsUtil.getRef(slots, field.getSlotId());
    }

    public void setFieldValue(String name, String descriptor, Reference ref) {
        Field field = clazz.getField(name, descriptor, false);
        SlotsUtil.setRef(slots, field.getSlotId(), ref);
    }
}
