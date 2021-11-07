package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class IAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x2e;
    }

    @Override
    public String getOpName() {
        return "iaload";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
