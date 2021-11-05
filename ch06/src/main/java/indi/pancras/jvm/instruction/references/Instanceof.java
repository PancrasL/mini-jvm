package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Frame;

public class Instanceof implements Instruction {
    @Override
    public int getOpCode() {
        return 0xc1;
    }

    @Override
    public String getOpName() {
        return "instanceof";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
