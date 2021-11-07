package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Dconst0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x0e;
    }

    @Override
    public String getOpName() {
        return "dconst_0";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(0.0d);
    }
}
