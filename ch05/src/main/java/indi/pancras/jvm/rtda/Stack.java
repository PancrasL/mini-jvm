package indi.pancras.jvm.rtda;

/**
 * @author PancrasL
 */
public class Stack<T> {
    private final int maxSize;
    private int size;
    private final Object[] data;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.data = new Object[maxSize];
        this.size = 0;
    }

    public void push(T val) {
        if (size == maxSize) {
            throw new StackOverflowError();
        }
        data[size++] = val;
    }

    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Java stack is empty");
        }
        size--;
        return (T) data[size];
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Java stack is empty");
        }
        return (T) data[size - 1];
    }
}
