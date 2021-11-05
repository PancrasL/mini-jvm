package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Fmul extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x6a;
    }

    @Override
    public String getOpName() {
        return "fmul";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.getOperandStack();
        float v2 = operandStack.popFloat();
        float v1 = operandStack.popFloat();
        operandStack.pushFloat(v1 * v2);
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        super.fetchOperands(reader);
    }
}
