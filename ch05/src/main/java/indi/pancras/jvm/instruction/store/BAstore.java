package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class BAstore extends BaseIndex8 {
    @Override
    public byte getOpCode() {
        return 0x54;
    }

    @Override
    public String getOpName() {
        return "bastore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
