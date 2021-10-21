package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Lstore3 extends BaseNoOperands {

    @Override
    public byte getOpCode() {
        return 0x42;
    }

    @Override
    public String getOpName() {
        return "lstore_3";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(3, val);
    }
}
