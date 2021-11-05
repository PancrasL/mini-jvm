package indi.pancras.jvm.instruction.math;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Ddiv extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x6f;
    }

    @Override
    public String getOpName() {
        return "ddiv";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();
        if (v2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        operandStack.pushDouble(v1 / v2);
    }
}
