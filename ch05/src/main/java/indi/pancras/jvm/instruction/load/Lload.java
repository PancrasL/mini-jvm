package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x16;
    }

    @Override
    public String getOpName() {
        return "lload";
    }

    @Override
    public void excute(Frame frame) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }
}
