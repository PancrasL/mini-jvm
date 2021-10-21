package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Iload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x15;
    }

    @Override
    public String getOpName() {
        return "iload";
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }
}
