package indi.pancras.jvm.instruction.store;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class AAstore extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x53;
    }

    @Override
    public String getOpName() {
        return "aastore";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
