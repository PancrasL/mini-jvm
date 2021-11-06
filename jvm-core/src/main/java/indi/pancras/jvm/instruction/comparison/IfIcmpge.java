package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class IfIcmpge extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa2;
    }

    @Override
    public String getOpName() {
        return "if_icmpge";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v1 >= v2) {
            branch(frame, offset);
        }
    }
}
