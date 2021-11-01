package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;


public class Lload1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1f;
    }

    @Override
    public String getOpName() {
        return "lload_1";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getLocalVars().getLong(1);
        frame.getOperandStack().pushLong(val);
    }
}
