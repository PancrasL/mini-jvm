package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.Reference;

/**
 * @author PancrasL
 */
public class Aload2 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x2c;
    }

    @Override
    public String getOpName() {
        return "aload2";
    }

    @Override
    public void execute(Frame frame) {
        Reference ref = frame.getLocalVars().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }
}
