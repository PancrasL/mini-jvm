package indi.pancras.jvm.instruction.math;

import indi.pancras.jvm.instruction.BaseIndex8;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;
import indi.pancras.jvm.rtda.LocalVars;
import lombok.Setter;

public class Iinc extends BaseIndex8 {

    @Setter
    private int constVal;

    @Override
    public int getOpCode() {
        return 0x84;
    }

    @Override
    public String getOpName() {
        return "iinc";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.read8();
        this.constVal = reader.read8();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += constVal;
        localVars.setInt(index, val);
    }
}
