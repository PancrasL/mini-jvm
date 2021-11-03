package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Lneg extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x75;
    }

    @Override
    public String getOpName() {
        return "lneg";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong(operandStack.popLong() * -1);
    }
}
