package indi.pancras.jvm.instruction.reserved;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.stack.Frame;

public class Impdep2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0xff;
    }

    @Override
    public String getOpName() {
        return "impdep2";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        throw new RuntimeException("Not implement: " + getOpName());
    }
}
