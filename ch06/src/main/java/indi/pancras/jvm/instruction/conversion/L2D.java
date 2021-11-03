package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class L2D extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x8a;
    }

    @Override
    public String getOpName() {
        return "l2d";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble((double) operandStack.popLong());
    }
}