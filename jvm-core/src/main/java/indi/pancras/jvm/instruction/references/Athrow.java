package indi.pancras.jvm.instruction.references;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Athrow implements Instruction {
    @Override
    public int getOpCode() {
        return 0xbf;
    }

    @Override
    public String getOpName() {
        return "athrow";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
