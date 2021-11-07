package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Dreturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xaf;
    }

    @Override
    public String getOpName() {
        return "dreturn";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
