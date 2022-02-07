package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNoOperands;
import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.stack.Frame;


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
        val = reader.read16();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(val);
    }
}
