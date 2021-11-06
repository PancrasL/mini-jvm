package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class F2I extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x8b;
    }

    @Override
    public String getOpName() {
        return "f2i";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((int) operandStack.popFloat());
    }
}
