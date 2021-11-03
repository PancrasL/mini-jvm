package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Iconst3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x06;
    }

    @Override
    public String getOpName() {
        return "iconst_3";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(3);
    }
}
