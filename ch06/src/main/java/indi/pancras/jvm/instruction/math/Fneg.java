package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Fneg extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x76;
    }

    @Override
    public String getOpName() {
        return "fneg";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat(operandStack.popFloat() * -1);
    }
}
