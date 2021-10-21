package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class Nop extends BaseNop {

    @Override
    public byte getOpCode() {
        return 0x00;
    }

    @Override
    public String getOpName() {
        return "nop";
    }

    @Override
    public void execute(Frame frame) {
        // Do nothing
    }
}
