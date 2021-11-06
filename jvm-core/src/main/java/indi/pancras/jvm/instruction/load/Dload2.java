package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Dload2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x28;
    }

    @Override
    public String getOpName() {
        return "dload_2";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getLocalVars().getDouble(2);
        frame.getOperandStack().pushDouble(val);
    }
}
