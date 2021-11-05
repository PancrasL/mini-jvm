package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Frame;

public class Getfield implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb4;
    }

    @Override
    public String getOpName() {
        return "getfield";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        throw new RuntimeException("Not implement: " + getOpName());
    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
