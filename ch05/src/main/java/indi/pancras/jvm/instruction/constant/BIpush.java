package indi.pancras.jvm.instruction.constant;

import indi.pancras.jvm.instruction.BaseNop;
import indi.pancras.jvm.instruction.BytecodeReader;
import indi.pancras.jvm.rtda.Frame;

/**
 * @author PancrasL
 */
public class BIpush extends BaseNop {
    private int val;

    @Override
    public byte getOpCode() {
        return 0x10;
    }

    @Override
    public String getOpName() {
        return "bipush";
    }

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.read8();
    }

    @Override
    public void excute(Frame frame) {
        frame.getOperandStack().pushInt(val);
    }
}
