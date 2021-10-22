package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

/**
 *
 */
public class Lshr extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x7b;
    }

    @Override
    public String getOpName() {
        return "lshr";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        long v2 = operandStack.popLong();
        long v1 = operandStack.popLong();
        long s = v2 & 0x3f;
        operandStack.pushLong(v1 >> s);
    }
}
