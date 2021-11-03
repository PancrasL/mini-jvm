package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class I2S extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x93;
    }

    @Override
    public String getOpName() {
        return "i2s";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushInt((short) operandStack.popInt());
    }
}
