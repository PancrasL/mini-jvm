package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Freturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xae;
    }

    @Override
    public String getOpName() {
        return "freturn";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
