package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Fsub extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x66;
    }

    @Override
    public String getOpName() {
        return "fsub";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();
        operandStack.pushFloat(v1 - v2);
    }
}
