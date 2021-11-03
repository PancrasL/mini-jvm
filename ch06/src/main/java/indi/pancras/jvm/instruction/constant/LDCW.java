package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class LDCW extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x13;
    }

    @Override
    public String getOpName() {
        return "ldc_w";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
