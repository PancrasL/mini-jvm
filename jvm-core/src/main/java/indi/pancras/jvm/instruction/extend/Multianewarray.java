package indi.pancras.jvm.instruction.extend;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Multianewarray implements Instruction {
    @Override
    public int getOpCode() {
        return 0xc5;
    }

    @Override
    public String getOpName() {
        return "multianewarray";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}
