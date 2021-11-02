package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class IfIcmpgt extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa3;
    }

    @Override
    public String getOpName() {
        return "if_icmpgt";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v1 > v2) {
            branch(frame, offset);
        }
    }
}
