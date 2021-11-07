package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class CAstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x55;
    }

    @Override
    public String getOpName() {
        return "castore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
