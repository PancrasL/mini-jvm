package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Lload3 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x21;
    }

    @Override
    public String getOpName() {
        return "lload_3";
    }

    @Override
    public void execute(Frame frame) {
        long val = frame.getLocalVars().getLong(3);
        frame.getOperandStack().pushLong(val);
    }
}
