package indi.pancras.jvm.rtda;

import java.util.Stack;

import indi.pancras.jvm.rtda.stack.Frame;
import lombok.Getter;
import lombok.Setter;


public class JThread {
    private final Stack<Frame> stack;
    private int maxStack;
    @Getter
    @Setter
    private int pc;

    public JThread(int maxStack) {
        this.stack = new Stack<>();
        this.maxStack = maxStack;
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
