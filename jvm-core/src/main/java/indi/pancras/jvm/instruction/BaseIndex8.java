package indi.pancras.jvm.instruction;

import indi.pancras.jvm.instruction.base.BytecodeReader;
import lombok.Setter;

/**
 * 需要访问局部变量表的指令基类，索引值由单字节给出，index字段表示局部变量表索引值
 *
 * @author PancrasL
 */
public abstract class BaseIndex8 implements Instruction {

    @Setter
    protected int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.read8();
    }
}
