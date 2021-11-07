package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class FAstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x51;
    }

    @Override
    public String getOpName() {
        return "fastore";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
