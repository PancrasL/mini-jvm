package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst4 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x07;
    }

    @Override
    public String getOpName() {
        return "iconst_4";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(4);
    }
}
