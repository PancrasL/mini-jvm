package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lconst0 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x09;
    }

    @Override
    public String getOpName() {
        return "lconst_0";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(0L);
    }
}
