package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;

public class Astore3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x4e;
    }

    @Override
    public String getOpName() {
        return "astore_3";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(3, ref);
    }
}
