package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class LAstore extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x50;
    }

    @Override
    public String getOpName() {
        return "lastore";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val = stack.popLong();
        int index = stack.popInt();
        Reference ref = stack.popRef();

        ref.getTarget().longs()[index] = val;
    }
}
