package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Fconst0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x0b;
    }

    @Override
    public String getOpName() {
        return "fconst_0";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
