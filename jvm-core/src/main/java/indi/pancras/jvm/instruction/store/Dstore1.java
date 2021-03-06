package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Dstore1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x48;
    }

    @Override
    public String getOpName() {
        return "dstore_1";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(1, val);
    }
}
