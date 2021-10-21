package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class SAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x35;
    }

    @Override
    public String getOpName() {
        return "saload";
    }

    @Override
    public void excute(Frame frame) {
        // TODO
    }
}
