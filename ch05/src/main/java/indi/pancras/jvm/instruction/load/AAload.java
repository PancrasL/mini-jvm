package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class AAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x32;
    }

    @Override
    public String getOpName() {
        return "aaload";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
