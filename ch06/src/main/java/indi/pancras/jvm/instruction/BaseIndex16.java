package indi.pancras.jvm.instruction;

/**
 * 需要访问运行时常量池的指令基类，常量池索引由两字节操作数给出，index表示索引值
 *
 * @author PancrasL
 */
public abstract class BaseIndex16 implements Instruction {
    private int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.read16();
    }
}
