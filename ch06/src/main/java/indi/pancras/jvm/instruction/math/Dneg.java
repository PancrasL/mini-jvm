package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Dneg extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x77;
    }

    @Override
    public String getOpName() {
        return "dneg";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble(operandStack.popDouble() * -1);
    }
}
