package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;

public class IfIcmpeq extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0x9f;
    }

    @Override
    public String getOpName() {
        return "if_icmpeq";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        int v2 = operandStack.popInt();
        int v1 = operandStack.popInt();
        if (v1 == v2) {
            branch(frame, offset);
        }
    }
}
