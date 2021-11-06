package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class D2F extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x90;
    }

    @Override
    public String getOpName() {
        return "d2f";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushFloat((float) operandStack.popDouble());
    }
}
