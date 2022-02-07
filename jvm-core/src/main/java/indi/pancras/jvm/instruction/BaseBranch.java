package indi.pancras.jvm.instruction;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import indi.pancras.jvm.rtda.stack.Frame;

/**
 * 跳转指令的基类，offset字段存放跳转偏移量
 */
public abstract class BaseBranch implements Instruction {
    protected int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.read16();
    }

    public void branch(Frame frame, int offset) {
        int pc = frame.getThread().getPc();
        frame.setNextPc(pc + offset);
    }
}
