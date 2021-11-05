package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Pop extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x57;
    }

    @Override
    public String getOpName() {
        return "pop";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
    }
}
