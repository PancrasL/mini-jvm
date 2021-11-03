package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Iconst2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x05;
    }

    @Override
    public String getOpName() {
        return "iconst_2";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(2);
    }
}
