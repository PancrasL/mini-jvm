package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Imul extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x68;
    }

    @Override
    public String getOpName() {
        return "imul";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        operandStack.pushInt(v1 * v2);
    }
}
