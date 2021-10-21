package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class IAstore extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x4f;
    }

    @Override
    public String getOpName() {
        return "iastore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
