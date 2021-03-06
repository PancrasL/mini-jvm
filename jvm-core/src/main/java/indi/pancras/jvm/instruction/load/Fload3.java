package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Fload3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x25;
    }

    @Override
    public String getOpName() {
        return "fload_3";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(3);
        frame.getOperandStack().pushFloat(val);
    }
}
