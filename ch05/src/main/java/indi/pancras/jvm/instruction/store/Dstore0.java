package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Dstore0 extends BaseNoOperands {
    @Override
    public byte getOpCode() {
        return 0x47;
    }

    @Override
    public String getOpName() {
        return "dstore_0";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(0, val);
    }
}
