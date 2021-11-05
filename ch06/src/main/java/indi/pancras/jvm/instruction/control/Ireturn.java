package indi.pancras.jvm.instruction.control;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Ireturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xac;
    }

    @Override
    public String getOpName() {
        return "ireturn";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
