package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Fload1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x23;
    }

    @Override
    public String getOpName() {
        return "fload_1";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(1);
        frame.getOperandStack().pushFloat(val);
    }
}
