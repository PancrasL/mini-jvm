package indi.pancras.jvm.instruction.reserved;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Breakpoint implements Instruction {
    @Override
    public int getOpCode() {
        return 0xca;
    }

    @Override
    public String getOpName() {
        return "breakpoint";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
