package indi.pancras.jvm.instruction.control;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Lreturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xad;
    }

    @Override
    public String getOpName() {
        return "lreturn";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
