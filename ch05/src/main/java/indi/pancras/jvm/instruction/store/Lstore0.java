package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Lstore0 extends BaseNoOperands {

    @Override
    public byte getOpCode() {
        return 0x3f;
    }

    @Override
    public String getOpName() {
        return "lstore_0";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(0, val);
    }
}
