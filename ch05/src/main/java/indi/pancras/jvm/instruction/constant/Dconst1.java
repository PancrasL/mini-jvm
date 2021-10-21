package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dconst1 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0f;
    }

    @Override
    public String getOpName() {
        return "dconst_1";
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushDouble(1.0d);
    }
}
