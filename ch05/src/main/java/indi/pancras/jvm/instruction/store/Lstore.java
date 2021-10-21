package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

public class Lstore extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x37;
    }

    @Override
    public String getOpName() {
        return "lstore";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(index, val);
    }
}
