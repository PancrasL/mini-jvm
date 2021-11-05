package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

public class Istore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x36;
    }

    @Override
    public String getOpName() {
        return "istore";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(index, val);
    }
}
