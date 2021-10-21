package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lload0 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x1e;
    }

    @Override
    public String getOpName() {
        return "lload_0";
    }

    @Override
    public void excute(Frame frame) {
        long val = frame.getLocalVars().getLong(0);
        frame.getOperandStack().pushLong(val);
    }
}
