package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class BAstore extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x54;
    }

    @Override
    public String getOpName() {
        return "bastore";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val = stack.popInt();
        int index = stack.popInt();
        Reference ref = stack.popRef();

        ref.getTarget().bytes()[index] = (byte) val;
    }
}
