package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Fload2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x24;
    }

    @Override
    public String getOpName() {
        return "fload_2";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(2);
        frame.getOperandStack().pushFloat(val);
    }
}
