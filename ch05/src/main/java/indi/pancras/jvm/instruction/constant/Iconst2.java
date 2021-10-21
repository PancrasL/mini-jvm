package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst2 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x05;
    }

    @Override
    public String getOpName() {
        return "iconst_2";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushInt(2);
    }
}
