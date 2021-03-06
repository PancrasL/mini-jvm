package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.stack.Frame;

public class Gotow extends BaseBranch {

    @Override
    public int getOpCode() {
        return 0xc8;
    }

    @Override
    public String getOpName() {
        return "goto_w";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.read32();
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
