package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iload2 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x1c;
    }

    @Override
    public String getOpName() {
        return "iload_2";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(2);
        frame.getOperandStack().pushInt(val);
    }
}
