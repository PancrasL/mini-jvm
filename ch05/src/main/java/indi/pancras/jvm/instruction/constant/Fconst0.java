package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fconst0 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0b;
    }

    @Override
    public String getOpName() {
        return "fconst_0";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
