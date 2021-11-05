package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;


public class SIpush extends BaseNoOperands {
    private int val;

    @Override
    public int getOpCode() {
        return 0x11;
    }

    @Override
    public String getOpName() {
        return "sipush";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.read16();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(val);
    }
}
