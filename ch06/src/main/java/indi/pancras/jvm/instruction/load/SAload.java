package indi.pancras.jvm.instruction.load;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.rtda.Frame;


public class SAload extends BaseIndex8 {
    @Override
    public int getOpCode() {
        return 0x35;
    }

    @Override
    public String getOpName() {
        return "saload";
    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
