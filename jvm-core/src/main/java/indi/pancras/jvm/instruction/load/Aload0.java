package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;


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
