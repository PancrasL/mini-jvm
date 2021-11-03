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

    public Frame(Thread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    @Override
    public String toString() {
        return "LocalVars: " + localVars +
                "OperandStack: " + operandStack + "\n";
    }
}
