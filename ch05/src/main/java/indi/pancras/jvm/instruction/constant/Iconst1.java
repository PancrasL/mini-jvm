package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst1 extends BaseNop {
    @Override
    public byte getOpCode() {
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
