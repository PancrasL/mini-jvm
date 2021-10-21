package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Fload2 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x24;
    }

    @Override
    public String getOpName() {
        return "fload_2";
    }

    @Override
    public void excute(Frame frame) {
        float val = frame.getLocalVars().getFloat(2);
        frame.getOperandStack().pushFloat(val);
    }
}
