package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Dup extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x59;
    }

    @Override
    public String getOpName() {
        return "dup";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
