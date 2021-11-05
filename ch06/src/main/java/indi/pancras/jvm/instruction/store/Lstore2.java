package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Lstore2 extends BaseNoOperands {

    @Override
    public int getOpCode() {
        return 0x41;
    }

    @Override
    public String getOpName() {
        return "lstore_2";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(2, val);
    }
}
