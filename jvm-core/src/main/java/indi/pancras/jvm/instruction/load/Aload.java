package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Reference;
import indi.pancras.jvm.rtda.stack.Frame;


public class Aload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x19;
    }

    @Override
    public String getOpName() {
        return "aload";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }
}
