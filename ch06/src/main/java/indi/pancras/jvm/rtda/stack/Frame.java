package indi.pancras.jvm.rtda.stack;

import indi.pancras.jvm.rtda.JThread;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Frame {
    private final LocalVars localVars;

    private final OperandStack operandStack;

    private final indi.pancras.jvm.rtda.JThread JThread;

    @Setter
    private int nextPc;

    public Frame(JThread JThread, int maxLocals, int maxStack) {
        this.JThread = JThread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    @Override
    public String toString() {
        return "LocalVars: " + localVars +
                "OperandStack: " + operandStack + "\n";
    }
}
