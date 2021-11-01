package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Dload3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x29;
    }

    @Override
    public String getOpName() {
        return "dload3";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getLocalVars().getDouble(3);
        frame.getOperandStack().pushDouble(val);
    }
}
