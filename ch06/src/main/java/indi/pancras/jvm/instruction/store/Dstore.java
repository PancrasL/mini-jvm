package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;

public class Dstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x39;
    }

    @Override
    public String getOpName() {
        return "dstore";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        frame.getLocalVars().setDouble(index, val);
    }
}
