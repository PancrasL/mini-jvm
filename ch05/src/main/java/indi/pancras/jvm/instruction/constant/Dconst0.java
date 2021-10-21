package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dconst0 extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x0e;
    }

    @Override
    public String getOpName() {
        return "dconst_0";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(0.0d);
    }
}
