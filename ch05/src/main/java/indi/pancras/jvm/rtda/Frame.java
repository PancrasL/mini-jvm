package indi.pancras.jvm.rtda;

import lombok.Getter;
import lombok.Setter;


@Getter
public class Frame {
    private final LocalVars localVars;

    private final OperandStack operandStack;

    private final Thread thread;

    @Setter
    private int nextPc;

    public Frame(LocalVars localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }
}
