package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iload0 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
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
