package indi.pancras.jvm.rtda.stack;

import indi.pancras.jvm.rtda.JThread;
import indi.pancras.jvm.rtda.heap.Method;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Frame {
    private final LocalVars localVars;
    private final OperandStack operandStack;

    private final JThread thread;
    private final Method method;

    @Setter
    private int nextPc;

    public Frame(JThread thread, Method method) {
        this.thread = thread;
        this.method = method;
        this.localVars = new LocalVars(method.getMaxLocals());
        this.operandStack = new OperandStack(method.getMaxStack());
    }

    public void revertNextPc(){
        this.nextPc = thread.getPc();
    }

    @Override
    public String toString() {
        return "LocalVars: " + localVars +
                "OperandStack: " + operandStack + "\n";
    }
}
