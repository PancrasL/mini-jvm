package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

public class Fstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x38;
    }

    @Override
    public String getOpName() {
        return "fstore";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(index, val);
    }
}
