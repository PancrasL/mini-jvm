package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

public class Dstore3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x4a;
    }

    @Override
    public String getOpName() {
        return "dstore_3";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(3, val);
    }
}
