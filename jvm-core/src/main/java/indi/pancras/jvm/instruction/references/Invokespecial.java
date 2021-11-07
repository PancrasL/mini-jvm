package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.stack.Frame;

public class Invokespecial extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public String getOpName() {
        return "invokespecial";
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }
}
