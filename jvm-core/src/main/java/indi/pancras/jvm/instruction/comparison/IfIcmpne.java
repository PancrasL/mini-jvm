package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class IfIcmpne extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa0;
    }

    @Override
    public String getOpName() {
        return "if_icmpne";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v1 != v2) {
            branch(frame, offset);
        }
    }
}
