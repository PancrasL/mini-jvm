package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.stack.OperandStack;

public class Arraylength extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xbe;
    }

    @Override
    public String getOpName() {
        return "arraylength";
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Reference arrRef = stack.popRef();
        int arrayLength = arrRef.getTarget().getArrayLength();
        stack.pushInt(arrayLength);
    }
}
