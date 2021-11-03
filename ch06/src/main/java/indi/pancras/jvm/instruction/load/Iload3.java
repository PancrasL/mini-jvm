package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Iload3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1d;
    }

    @Override
    public String getOpName() {
        return "iload_3";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(3);
        frame.getOperandStack().pushInt(val);
    }
}
