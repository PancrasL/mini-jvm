package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class BAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x33;
    }

    @Override
    public String getOpName() {
        return "baload";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
