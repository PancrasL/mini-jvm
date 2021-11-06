package indi.pancras.jvm.instruction.comparison;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Dcmpg extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x98;
    }

    @Override
    public String getOpName() {
        return "dcmpg";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        double v2 = operandStack.popDouble();
        double v1 = operandStack.popDouble();
        if (v1 > v2) {
            operandStack.pushInt(1);
        } else if (v1 == v2) {
            operandStack.pushInt(0);
        } else if (v1 < v2) {
            operandStack.pushInt(-1);
        } else {
            operandStack.pushInt(1);
        }
    }
}
