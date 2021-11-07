package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class DAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x31;
    }

    @Override
    public String getOpName() {
        return "daload";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
