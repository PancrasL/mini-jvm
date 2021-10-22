package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class Ifgt extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0x9d;
    }

    @Override
    public String getOpName() {
        return "ifgt";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int i = operandStack.popInt();
        if (i < 0) {
            branch(frame, offset);
        }
    }
}
