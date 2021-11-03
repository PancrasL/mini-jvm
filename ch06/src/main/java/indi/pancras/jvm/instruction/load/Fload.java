package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;


public class Fload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x17;
    }

    @Override
    public String getOpName() {
        return "fload";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
