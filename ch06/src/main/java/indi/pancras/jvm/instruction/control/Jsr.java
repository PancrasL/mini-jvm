package indi.pancras.jvm.instruction.control;


import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Jsr extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xa8;
    }

    @Override
    public String getOpName() {
        return "jsr";
    }

    @Override
    public void execute(Frame frame) {

    }
}
