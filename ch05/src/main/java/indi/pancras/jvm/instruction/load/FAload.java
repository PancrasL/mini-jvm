package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class FAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x30;
    }

    @Override
    public String getOpName() {
        return "faload";
    }

    @Override
    public void excute(Frame frame) {
        // TODO
    }
}
