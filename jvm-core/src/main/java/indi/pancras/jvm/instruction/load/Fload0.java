package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Fload0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x22;
    }

    @Override
    public String getOpName() {
        return "fload_0";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(0);
        frame.getOperandStack().pushFloat(val);
    }
}
