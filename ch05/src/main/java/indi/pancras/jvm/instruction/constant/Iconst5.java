package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst5 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x08;
    }

    @Override
    public String getOpName() {
        return "iconst_5";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushInt(5);
    }
}
