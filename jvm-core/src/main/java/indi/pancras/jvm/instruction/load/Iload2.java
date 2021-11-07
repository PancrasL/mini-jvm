package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;


public class Iload2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1c;
    }

    @Override
    public String getOpName() {
        return "iload_2";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(2);
        frame.getOperandStack().pushInt(val);
    }
}
