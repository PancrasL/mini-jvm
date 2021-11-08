package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.rtda.stack.Frame;

/**
 * Return void from method
 */
public class Return extends BaseNoOperands {
    @Override
    public int getOpCode() {
        return 0xb1;
    }

    @Override
    public String getOpName() {
        return "return";
    }

    @Override
    public void execute(Frame frame) {
        frame.getThread().popFrame();
    }
}
