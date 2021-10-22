package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dload0 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x26;
    }

    @Override
    public String getOpName() {
        return "dload0";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushDouble(val);
    }
}