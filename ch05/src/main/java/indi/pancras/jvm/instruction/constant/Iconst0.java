package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconst0 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x03;
    }

    @Override
    public String getOpName() {
        return "iconst_0";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushInt(0);
    }
}
