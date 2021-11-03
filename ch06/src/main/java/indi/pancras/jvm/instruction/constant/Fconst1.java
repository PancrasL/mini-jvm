package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Fconst1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x0c;
    }

    @Override
    public String getOpName() {
        return "fconst_1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
