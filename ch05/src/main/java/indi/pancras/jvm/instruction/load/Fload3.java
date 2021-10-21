package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fload3 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x25;
    }

    @Override
    public String getOpName() {
        return "fload_3";
    }

    @Override
    public void execute(Frame frame) {
        float val = frame.getLocalVars().getFloat(3);
        frame.getOperandStack().pushFloat(val);
    }
}
