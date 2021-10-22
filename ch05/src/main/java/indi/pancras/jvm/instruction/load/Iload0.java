package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iload0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x1a;
    }

    @Override
    public String getOpName() {
        return "iload_0";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(0);
        frame.getOperandStack().pushInt(val);
    }
}
