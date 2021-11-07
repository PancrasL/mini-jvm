package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class L2I extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x88;
    }

    @Override
    public String getOpName() {
        return "l2i";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((int) operandStack.popLong());
    }
}
