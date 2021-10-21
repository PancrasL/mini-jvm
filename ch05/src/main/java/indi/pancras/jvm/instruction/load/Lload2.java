package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lload2 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x20;
    }

    @Override
    public String getOpName() {
        return "lload_2";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getLocalVars().getLong(2);
        frame.getOperandStack().pushLong(val);
    }
}
