package indi.pancras.jvm.rtda.base;

import indi.pancras.jvm.rtda.heap.JObject;

public class Slot {
    public int val;
    public Reference ref;
    private final boolean isRef;

    public Slot(JObject ref) {
        this.val = 0;
        this.ref = new Reference(ref);
        this.isRef = true;
    }

    public Slot(int val) {
        this.val = val;
        this.ref = null;
        this.isRef = false;
    }

    public Slot(Reference ref) {
        this.val = 0;
        this.ref = ref;
        this.isRef = true;
    }

    @Override
    public String toString() {
        return isRef ? ref.toString() : String.valueOf(val);
    }
}
