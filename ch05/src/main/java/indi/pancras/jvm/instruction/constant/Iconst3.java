package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst3 extends BaseNop {
    @Override
    public byte getOpCode() {
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
