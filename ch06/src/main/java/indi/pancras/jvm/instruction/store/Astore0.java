package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;

public class Astore0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x4b;
    }

    @Override
    public String getOpName() {
        return "astore_0";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(0, ref);
    }
}
