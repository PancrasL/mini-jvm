package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class AconstNull extends BaseNoOperands {
    @Override
    public int getOpCode() {
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
