package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class SAstore extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x56;
    }

    @Override
    public String getOpName() {
        return "sastore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
