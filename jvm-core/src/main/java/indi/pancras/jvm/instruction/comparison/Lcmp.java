package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Lcmp extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x94;
    }

    @Override
    public String getOpName() {
        return "lcmp";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();
        if (v1 > v2) {
            operandStack.pushInt(1);
        } else if (v1 == v2) {
            operandStack.pushInt(0);
        } else {
            operandStack.pushInt(-1);
        }
    }
}
