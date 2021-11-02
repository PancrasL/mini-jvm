package indi.pancras.jvm.instruction.reserved;

import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.instruction.Instruction;
import indi.pancras.jvm.rtda.Frame;

public class Impdep1 implements Instruction {
    @Override
    public int getOpCode() {
        return 0xfe;
    }

    @Override
    public String getOpName() {
        return "impdep1";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        // TODO
    }
}