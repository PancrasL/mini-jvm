package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Ifle extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0x9e;
    }

    @Override
    public String getOpName() {
        return "ifle";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int i = operandStack.popInt();
        if (i <= 0) {
            branch(frame, offset);
        }
    }
}
