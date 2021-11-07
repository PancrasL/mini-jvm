package indi.pancras.jvm.instruction.stack;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.Slot;
import indi.pancras.jvm.rtda.stack.OperandStack;

/**
 * 将栈顶的2个Slot的值复制并插入到栈顶的3个Slot的值下面
 *
 * @author PancrasL
 */
public class Dup2x1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x5d;
    }

    @Override
    public String getOpName() {
        return "dup2_x1";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();

        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
