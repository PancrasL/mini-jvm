package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Fstore1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x44;
    }

    @Override
    public String getOpName() {
        return "fstore_1";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(1, val);
    }
}
