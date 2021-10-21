package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class AconstNull extends BaseNop {
    @Override
    public byte getOpCode() {
        return 0x01;
    }

    @Override
    public String getOpName() {
        return "aconst_null";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(null);
    }
}
