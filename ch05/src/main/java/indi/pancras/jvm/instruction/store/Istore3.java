package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Istore3 extends BaseNoOperands {
    @Override
    public byte getOpCode() {
        return 0x3e;
    }

    @Override
    public String getOpName() {
        return "istore_3";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(3, val);
    }
}
