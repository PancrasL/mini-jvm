package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class Dload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x18;
    }

    @Override
    public String getOpName() {
        return "dload";
    }

    @Override
    public void execute(Frame frame) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }
}
