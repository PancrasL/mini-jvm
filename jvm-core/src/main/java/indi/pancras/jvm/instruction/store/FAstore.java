package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class FAstore extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x51;
    }

    @Override
    public String getOpName() {
        return "fastore";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val = stack.popFloat();
        int index = stack.popInt();
        Reference ref = stack.popRef();

        ref.getTarget().floats()[index] = val;
    }
}
