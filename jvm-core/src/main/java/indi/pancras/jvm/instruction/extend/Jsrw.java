package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Jsrw implements Instruction {
    @Override
    public int getOpCode() {
        return 0xc9;
    }

    @Override
    public String getOpName() {
        return "jsr_w";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
