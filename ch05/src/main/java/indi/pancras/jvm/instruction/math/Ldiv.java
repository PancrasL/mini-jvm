package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Ldiv extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x6d;
    }

    @Override
    public String getOpName() {
        return "ldiv";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();
        if (v2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        operandStack.pushLong(v1 / v2);
    }
}
