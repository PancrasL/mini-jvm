package indi.pancras.jvm.rtda.heap;

import java.util.ArrayList;
import java.util.List;

import indi.pancras.jvm.rtda.base.Slot;

public class JObject {
    private final JClass clazz;
    private final List<Slot> slots;

    public JObject(JClass clazz, int slotsCnt) {
        this.clazz = clazz;
        this.slots = new ArrayList<>(slotsCnt);
    }
}
