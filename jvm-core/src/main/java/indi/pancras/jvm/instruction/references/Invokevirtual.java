package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BaseIndex16;
import indi.pancras.jvm.rtda.stack.Frame;

public class Invokevirtual extends BaseIndex16 {
    @Override
    public int getOpCode() {
        return 0xb6;
    }

    @Override
    public String getOpName() {
        return "invokevirtual";
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
