package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.stack.Frame;


public class CAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
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
