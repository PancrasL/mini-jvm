package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.OperandStack;
import indi.pancras.jvm.rtda.Reference;

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
        Reference reference = operandStack.popRef();
        if (reference.getValue() == null) {
            branch(frame, offset);
        }
    }
}