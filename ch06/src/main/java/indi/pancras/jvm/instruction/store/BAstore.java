package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class BAstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x54;
    }

    @Override
    public String getOpName() {
        return "bastore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
