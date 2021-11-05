package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.base.Reference;


public class Aload0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x2a;
    }

    @Override
    public String getOpName() {
        return "aload_0";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(0);
        frame.getOperandStack().pushRef(ref);
    }
}
