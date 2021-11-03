package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Ifeq extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0x99;
    }

    @Override
    public String getOpName() {
        return "ifeq";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int i = operandStack.popInt();
        if (i == 0) {
            branch(frame, offset);
        }
    }
}
