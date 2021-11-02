package indi.pancras.jvm.rtda;

import lombok.Getter;
import lombok.Setter;


public class Thread {
    private final Stack<Frame> stack;
    @Getter
    @Setter
    private int pc;

    public Thread(int maxStack) {
        this.stack = new Stack<>(maxStack);
    }

    public void pushFrame(Frame frame) {
        stack.push(frame);
    }

    public Frame popFrame() {
        return stack.pop();
    }

    public Frame topFrame() {
        return stack.peek();
    }
}
