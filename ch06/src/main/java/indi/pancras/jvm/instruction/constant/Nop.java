package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Nop extends BaseNoOperands {

    @Override
    public int getOpCode() {
        return 0x00;
    }

    @Override
    public String getOpName() {
        return "nop";
    }

    @Override
    public void execute(Frame frame) {
        // Do nothing
    }
}
