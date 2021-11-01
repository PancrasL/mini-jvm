package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.Reference;


public class Aload1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x2b;
    }

    @Override
    public String getOpName() {
        return "aload1";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(1);
        frame.getOperandStack().pushRef(ref);
    }
}
