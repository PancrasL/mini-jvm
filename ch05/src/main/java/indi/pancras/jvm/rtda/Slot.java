package indi.pancras.jvm.rtda;

public class Slot {
    public int val;
    public Reference ref;
    private boolean isRef;

    public Slot(Reference ref) {
        this.val = 0;
        this.ref = ref;
        this.isRef = true;
    }

    public Slot(int val) {
        this.val = val;
        this.ref = null;
        this.isRef = false;
    }

    @Override
    public String toString() {
        return isRef ? ref.toString() : String.valueOf(val);
    }
}
