package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class IfIcmplt extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa1;
    }

    @Override
    public String getOpName() {
        return "if_icmplt";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v2 > v1) {
            branch(frame, offset);
        }
    }
}
