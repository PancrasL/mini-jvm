package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lconst1 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0a;
    }

    @Override
    public String getOpName() {
        return "lconst_1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(1L);
    }
}
