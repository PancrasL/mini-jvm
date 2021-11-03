package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Fconst2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x0d;
    }

    @Override
    public String getOpName() {
        return "fconst_2";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(2.0f);
    }
}
