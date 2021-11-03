package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Dstore2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x49;
    }

    @Override
    public String getOpName() {
        return "dstore_2";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(2, val);
    }
}
