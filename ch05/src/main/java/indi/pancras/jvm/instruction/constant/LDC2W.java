package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class LDC2W extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x14;
    }

    @Override
    public String getOpName() {
        return "ldc2_w";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
