package indi.pancras.jvm.rtda;

import lombok.Getter;
import lombok.Setter;

/**
 * @author PancrasL
 */
@Getter
public class Frame {
    private LocalVars localVars;

    private OperandStack operandStack;

    private Thread thread;

    @Setter
    private int nextPc;

    public Frame(LocalVars localVars, OperandStack operandStack, Thread thread) {
        this.localVars = localVars;
        this.operandStack = operandStack;
        this.thread = thread;
    }
}
