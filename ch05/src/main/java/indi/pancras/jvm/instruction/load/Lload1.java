package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lload1 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x1f;
    }

    @Override
    public String getOpName() {
        return "lload_1";
    }

    @Override
    public void excute(Frame frame) {
        long val = frame.getLocalVars().getLong(1);
        frame.getOperandStack().pushLong(val);
    }
}
