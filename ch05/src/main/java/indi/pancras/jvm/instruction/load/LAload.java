package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class LAload extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x2f;
    }

    @Override
    public String getOpName() {
        return "laload";
    }

    @Override
    public void excute(Frame frame) {
        // TODO
    }
}
