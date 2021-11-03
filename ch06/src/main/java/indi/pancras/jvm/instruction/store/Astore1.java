package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.stack.Frame;

public class Astore1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
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
