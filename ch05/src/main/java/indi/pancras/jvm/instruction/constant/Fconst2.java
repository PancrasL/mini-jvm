package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fconst2 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0d;
    }

    @Override
    public String getOpName() {
        return "fconst_2";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushFloat(2.0f);
    }
}
