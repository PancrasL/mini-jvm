package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iconstm1 extends BaseNoOperands {
    @Override
    public byte getOpCode() {
        return 0x02;
    }

    @Override
    public String getOpName() {
        return "iconst_m1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(-1);
    }
}
