package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fconst1 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0c;
    }

    @Override
    public String getOpName() {
        return "fconst_1";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
