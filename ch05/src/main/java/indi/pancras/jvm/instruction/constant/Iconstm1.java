package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconstm1 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x02;
    }

    @Override
    public String getOpName() {
        return "iconst_m1";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushInt(-1);
    }
}
