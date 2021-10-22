package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class L2F extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x89;
    }

    @Override
    public String getOpName() {
        return "l2f";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat((float) operandStack.popLong());
    }
}
