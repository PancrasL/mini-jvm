package indi.pancras.jvm.rtda;

import indi.pancras.jvm.rtda.heap.Method;
import indi.pancras.jvm.rtda.stack.LocalVars;
import indi.pancras.jvm.rtda.stack.OperandStack;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Frame {
    private final LocalVars localVars;
    private final OperandStack operandStack;

    private final JThread jThread;
    private final Method method;

    @Setter
    private int nextPc;

    public Frame(JThread thread, Method method) {
        this.jThread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStack());
    }

    @Override
    public String toString() {
        return "LocalVars: " + localVars +
                "OperandStack: " + operandStack + "\n";
    }
}
