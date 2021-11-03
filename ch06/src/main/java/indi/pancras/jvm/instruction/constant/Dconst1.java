package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Dconst1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x0f;
    }

    @Override
    public String getOpName() {
        return "dconst_1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(1.0d);
    }
}
