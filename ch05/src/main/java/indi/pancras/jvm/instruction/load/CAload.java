package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class CAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x34;
    }

    @Override
    public String getOpName() {
        return "caload";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
