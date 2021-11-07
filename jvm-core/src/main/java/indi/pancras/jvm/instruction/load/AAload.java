package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class AAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x32;
    }

    @Override
    public String getOpName() {
        return "aaload";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
