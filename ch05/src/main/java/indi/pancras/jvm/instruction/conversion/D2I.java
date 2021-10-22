package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class D2I extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x8e;
    }

    @Override
    public String getOpName() {
        return "d2i";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((int) operandStack.popDouble());
    }
}