package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Ifnonnull extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xc7;
    }

    @Override
    public String getOpName() {
        return "ifnonnull";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Reference obj = operandStack.popRef();
        if (obj != null && !obj.targetIsNull()) {
            branch(frame, offset);
        }
    }
}
