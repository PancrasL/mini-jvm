package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Iload1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1b;
    }

    @Override
    public String getOpName() {
        return "iload_1";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(1);
        frame.getOperandStack().pushInt(val);
    }
}
