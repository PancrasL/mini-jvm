package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

public class Fstore2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x45;
    }

    @Override
    public String getOpName() {
        return "fstore_2";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(2, val);
    }
}
