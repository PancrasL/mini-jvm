package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Areturn extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xb0;
    }

    @Override
    public String getOpName() {
        return "areturn";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
