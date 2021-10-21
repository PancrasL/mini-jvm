package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class SIpush extends BaseNop {
    private int val;

    @Override
    public byte getOpCode() {
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
