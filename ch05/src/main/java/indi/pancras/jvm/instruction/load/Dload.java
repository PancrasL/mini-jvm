package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x18;
    }

    @Override
    public String getOpName() {
        return "dload";
    }

    @Override
    public void excute(Frame frame) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
