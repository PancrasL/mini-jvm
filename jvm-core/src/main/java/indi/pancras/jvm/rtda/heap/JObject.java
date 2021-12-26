package indi.pancras.jvm.rtda.heap;

import indi.pancras.jvm.rtda.Slot;
import lombok.Getter;

@Getter
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

}
