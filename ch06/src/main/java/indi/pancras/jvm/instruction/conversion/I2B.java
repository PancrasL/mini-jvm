package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class I2B extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x91;
    }

    @Override
    public String getOpName() {
        return "i2b";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((byte) operandStack.popInt());
    }
}
