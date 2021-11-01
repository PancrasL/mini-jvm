package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Lconst0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x09;
    }

    @Override
    public String getOpName() {
        return "lconst_0";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(0L);
    }
}
