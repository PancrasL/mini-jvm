package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class D2L extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x8f;
    }

    @Override
    public String getOpName() {
        return "d2l";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong((long) operandStack.popDouble());
    }
}
