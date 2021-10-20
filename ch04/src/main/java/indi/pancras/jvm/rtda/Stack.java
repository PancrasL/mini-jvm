package indi.pancras.jvm.rtda;

/**
 * @author PancrasL
 */
public class Stack<T> {
    private int maxSize;
    private int size;
    private T[] data;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        size = 0;
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
        return data[size];
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Java stack is empty");
        }
        return data[size - 1];
    }
}
