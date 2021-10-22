package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Dload1 extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0x27;
    }

    @Override
    public String getOpName() {
        return "dload1";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getLocalVars().getDouble(1);
        frame.getOperandStack().pushDouble(val);
    }
}
