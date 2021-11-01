package indi.pancras.jvm.rtda;


public class Frame {
    private LocalVars localVars;

    private OperandStack operandStack;

    private Thread thread;

    private int nextPc;

    public Frame(LocalVars localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }
}
