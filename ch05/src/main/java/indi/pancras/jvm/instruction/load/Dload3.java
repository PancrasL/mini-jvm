package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dload3 extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x29;
    }

    @Override
    public String getOpName() {
        return "dload3";
    }

    @Override
    public void excute(Frame frame) {
        double val = frame.getLocalVars().getDouble(3);
        frame.getOperandStack().pushDouble(val);
    }
}
