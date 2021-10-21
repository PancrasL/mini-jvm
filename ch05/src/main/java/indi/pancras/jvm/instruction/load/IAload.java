package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class IAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x2e;
    }

    @Override
    public String getOpName() {
        return "iaload";
    }

    @Override
    public void excute(Frame frame) {
        // TODO
    }
}
