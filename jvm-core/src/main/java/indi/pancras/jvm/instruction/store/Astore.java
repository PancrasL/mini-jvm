package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.Reference;


public class Astore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x3a;
    }

    @Override
    public String getOpName() {
        return "astore";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(index, ref);
    }
}
