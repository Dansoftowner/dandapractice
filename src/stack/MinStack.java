package stack;

import java.util.Objects;

public class MinStack<T extends Comparable<T>> {

    private final MyStack<T> stack;
    private final MyStack<T> minStack;

    public MinStack() {
        stack = new MyStack<>();
        minStack = new MyStack<>();
    }

    public T push(T item) {
        // 1, 2
        stack.push(item);
        if (minStack.isEmpty() || minStack.peek().compareTo(item) >= 0)
            minStack.push(item);
        return item;
    }

    public T pop() {
        if (Objects.equals(minStack.peek(), stack.peek()))
            minStack.pop();
        return stack.pop();
    }

    public T min() {
        return minStack.peek();
    }


}
