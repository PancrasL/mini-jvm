package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;
import indi.pancras.jvm.rtda.Reference;


public class Aload3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x2d;
    }

    @Override
    public String getOpName() {
        return "aload_3";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(3);
        frame.getOperandStack().pushRef(ref);
    }
}
