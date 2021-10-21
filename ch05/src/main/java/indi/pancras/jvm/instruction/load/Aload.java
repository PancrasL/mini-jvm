package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.Reference;

/**
 * @author PancrasL
 */
public class Aload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x19;
    }

    @Override
    public String getOpName() {
        return "aload";
    }

    @Override
    public void excute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }
}
