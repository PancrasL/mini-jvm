package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Ineg extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x74;
    }

    @Override
    public String getOpName() {
        return "ineg";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt(operandStack.popInt() * -1);
    }
}
