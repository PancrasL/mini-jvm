package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class F2L extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x8c;
    }

    @Override
    public String getOpName() {
        return "f2l";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushLong((long) operandStack.popFloat());
    }
}
