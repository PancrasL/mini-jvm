package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Fstore0 extends BaseNoOperands {
    @Override
    public byte getOpCode() {
        return 0x43;
    }

    @Override
    public String getOpName() {
        return "fstore_0";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(0, val);
    }
}
