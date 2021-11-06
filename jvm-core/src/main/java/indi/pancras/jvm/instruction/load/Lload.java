package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;


public class Lload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x16;
    }

    @Override
    public String getOpName() {
        return "lload";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
