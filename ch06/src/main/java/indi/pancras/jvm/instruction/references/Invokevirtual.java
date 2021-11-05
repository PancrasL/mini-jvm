package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Frame;

public class Invokevirtual implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb6;
    }

    @Override
    public String getOpName() {
        return "invokevirtual";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
