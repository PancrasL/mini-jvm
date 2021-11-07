package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 *
 */
public class Ishr extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x7a;
    }

    @Override
    public String getOpName() {
        return "ishr";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        int s = v2 & 0x1f;
        operandStack.pushInt(v1 >> s);
    }
}
