package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Iconst1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x04;
    }

    @Override
    public String getOpName() {
        return "iconst_1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(1);
    }
}
