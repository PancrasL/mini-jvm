package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Invokeinterface implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb9;
    }

    @Override
    public String getOpName() {
        return "invokeinterface";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
