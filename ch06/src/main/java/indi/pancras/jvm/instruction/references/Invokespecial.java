package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Frame;

public class Invokespecial implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public String getOpName() {
        return "invokespecial";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
