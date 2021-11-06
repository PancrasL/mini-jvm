package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * Return from subroutine
 */
public class Ret extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xa9;
    }

    @Override
    public String getOpName() {
        return "ret";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
