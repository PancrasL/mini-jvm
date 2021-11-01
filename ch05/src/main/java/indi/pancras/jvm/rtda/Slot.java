package indi.pancras.jvm.rtda;


public class Slot {
    public int val;
    public Reference ref;

    public Slot(Reference ref) {
        this.val = 0;
        this.ref = ref;
    }

    public Slot(int val) {
        this.val = val;
        this.ref = null;
    }
}
