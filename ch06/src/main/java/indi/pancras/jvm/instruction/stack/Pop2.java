package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Pop2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x58;
    }

    @Override
    public String getOpName() {
        return "pop2";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
        stack.popSlot();
    }
}
