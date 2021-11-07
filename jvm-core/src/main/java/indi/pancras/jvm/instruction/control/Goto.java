package indi.pancras.jvm.instruction.control;

import indi.pancras.jvm.instruction.BaseBranch;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.stack.Frame;


public class Goto extends BaseBranch {
    @Override
    public int getOpCode() {
        return 0xa7;
    }

    @Override
    public String getOpName() {
        return "goto";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.read16();
    }

    @Override
    public void execute(Frame frame) {
        branch(frame, offset);
    }
}
