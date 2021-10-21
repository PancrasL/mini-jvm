package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class BAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x33;
    }

    @Override
    public String getOpName() {
        return "baload";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
