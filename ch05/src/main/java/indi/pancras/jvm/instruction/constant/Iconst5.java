package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst5 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x08;
    }

    @Override
    public String getOpName() {
        return "iconst_5";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(5);
    }
}
