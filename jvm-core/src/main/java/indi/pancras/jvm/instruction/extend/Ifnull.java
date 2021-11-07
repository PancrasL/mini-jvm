package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Ifnull extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xc6;
    }

    @Override
    public String getOpName() {
        return "ifnull";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Reference ref = operandStack.popRef();
        if (ref.targetIsNull()) {
            branch(frame, offset);
        }
    }
}
