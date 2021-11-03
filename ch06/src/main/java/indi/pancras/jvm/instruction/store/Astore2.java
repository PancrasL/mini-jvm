package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.base.Reference;
import indi.pancras.jvm.rtda.stack.Frame;

public class Astore2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x4d;
    }

    @Override
    public String getOpName() {
        return "astore_2";
    }

    @Override
    public void execute(Frame frame) {
        Reference val = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(2, val);
    }
}
