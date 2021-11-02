package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;
import indi.pancras.jvm.rtda.Reference;

public class IfAcmpeq extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa5;
    }

    @Override
    public String getOpName() {
        return "if_acmpeq";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Reference v2 = operandStack.popRef();
        Reference v1 = operandStack.popRef();
        if (v1.equals(v2)) {
            branch(frame, offset);
        }
    }
}
