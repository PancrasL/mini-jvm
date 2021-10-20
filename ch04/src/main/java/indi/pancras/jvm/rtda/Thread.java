package indi.pancras.jvm.rtda;

import lombok.Getter;
import lombok.Setter;

/**
 * @author PancrasL
 */
public class Thread {
    @Getter
    @Setter
    private int pc;

    private Stack<Frame> stack;

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
