package indi.pancras.jvm.instruction;

import indi.pancras.jvm.rtda.stack.Frame;


public interface Instruction {
    /**
     * 获取指令值
     *
     * @return 指令值
     */
    int getOpCode();

    /**
     * 获取指令助记符
     *
     * @return 指令助记符
     */
    String getOpName();

    /**
     * 取操作数
     *
     * @param reader 字节码指令读取器
     */
    void fetchOperands(BytecodeReader reader);

    /**
     * 执行指令
     *
     * @param frame 当前栈帧
     */
    void execute(Frame frame);
}
