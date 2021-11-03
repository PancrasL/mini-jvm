package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class LDC extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public String getOpName() {
        return "ldc";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
