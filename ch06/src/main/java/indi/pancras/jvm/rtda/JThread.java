package indi.pancras.jvm.rtda;

import java.util.Stack;

import lombok.Getter;
import lombok.Setter;


public class JThread {
    private final Stack<Frame> stack;
    private final int maxStack;
    @Getter
    @Setter
    private int pc;

    public JThread(int maxStack) {
        this.stack = new Stack<>();
        this.maxStack = maxStack;
    }

    public JThread() {
        this(1024);
    }

    public void pushFrame(Frame frame) {
        if (stack.size() == maxStack) {
            throw new StackOverflowError();
        }
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame topFrame() {
        return stack.peek();
    }
}
