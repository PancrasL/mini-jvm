package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fload0 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x22;
    }

    @Override
    public String getOpName() {
        return "fload_0";
    }

    @Override
    public void excute(Frame frame) {
        float val = frame.getLocalVars().getFloat(0);
        frame.getOperandStack().pushFloat(val);
    }
}
