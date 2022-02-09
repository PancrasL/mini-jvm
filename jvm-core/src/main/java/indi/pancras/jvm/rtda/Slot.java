package indi.pancras.jvm.rtda;

public class Slot {
    private final int val;
    private final Reference ref;
    private final boolean isRef;

    public Slot() {
        val = 0;
        ref = null;
        isRef = false;
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

    public int getVal() {
        if (!isRef) {
            return val;
        }
        throw new RuntimeException("Slot value is Reference");
    }

    public Reference getRef() {
        if (isRef) {
            return ref;
        }
        throw new RuntimeException("Slot value is not Reference");
    }

    @Override
    public String toString() {
        if (isRef) {
            return ref == null ? "null" : ref.toString();
        }
        return String.valueOf(val);
    }
}
