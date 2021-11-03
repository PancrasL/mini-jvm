package indi.pancras.jvm.instruction.conversion;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class I2D extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x87;
    }

    @Override
    public String getOpName() {
        return "i2d";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.pushDouble((double) operandStack.popInt());
    }
}
