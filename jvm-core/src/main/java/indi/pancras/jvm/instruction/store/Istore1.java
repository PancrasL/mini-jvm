package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Istore1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x3c;
    }

    @Override
    public String getOpName() {
        return "istore_1";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(1, val);
    }
}
