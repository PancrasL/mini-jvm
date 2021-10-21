package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.Reference;

public class Astore1 extends BaseNoOperands {
    @Override
    public byte getOpCode() {
        return 0x4c;
    }

    @Override
    public String getOpName() {
        return "astore_1";
    }

    @Override
    public void execute(Frame frame) {
        Reference val = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(1, val);
    }
}
