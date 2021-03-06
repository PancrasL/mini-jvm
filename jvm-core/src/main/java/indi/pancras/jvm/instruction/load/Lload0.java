package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Lload0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1e;
    }

    @Override
    public String getOpName() {
        return "lload_0";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getLocalVars().getLong(0);
        frame.getOperandStack().pushLong(val);
    }
}
