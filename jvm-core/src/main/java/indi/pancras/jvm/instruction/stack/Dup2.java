package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;


public class Dup2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x5c;
    }

    @Override
    public String getOpName() {
        return "dup2";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
