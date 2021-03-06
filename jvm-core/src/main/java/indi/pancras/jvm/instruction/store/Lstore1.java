package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Lstore1 extends BaseNoOperands {

    @Override
    public int getOpCode() {
        return 0x40;
    }

    @Override
    public String getOpName() {
        return "lstore_1";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(1, val);
    }
}
