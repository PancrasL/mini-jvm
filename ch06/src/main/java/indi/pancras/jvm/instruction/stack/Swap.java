package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Slot;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Swap extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x5f;
    }

    @Override
    public String getOpName() {
        return "swap";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
    }
}
