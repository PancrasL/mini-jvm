package indi.pancras.jvm.instruction;

import indi.pancras.jvm.instruction.base.BytecodeReader;

/**
 * 无操作数指令的基类
 *
 * @author PancrasL
 */
public abstract class BaseNoOperands implements Instruction {
    @Override
    public void fetchOperands(BytecodeReader reader) {
        // Do nothing
    }
}
