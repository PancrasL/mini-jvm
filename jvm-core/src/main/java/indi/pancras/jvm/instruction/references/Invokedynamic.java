package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Invokedynamic implements Instruction {
    @Override
    public int getOpCode() {
        return 0xba;
    }

    @Override
    public String getOpName() {
        return "invokedynamic";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
