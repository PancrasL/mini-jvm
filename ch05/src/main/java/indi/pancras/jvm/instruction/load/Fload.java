package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x17;
    }

    @Override
    public String getOpName() {
        return "fload";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }
}
