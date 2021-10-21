package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iload3 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x1d;
    }

    @Override
    public String getOpName() {
        return "iload_3";
    }

    @Override
    public void excute(Frame frame) {
        int val = frame.getLocalVars().getInt(3);
        frame.getOperandStack().pushInt(val);
    }
}
