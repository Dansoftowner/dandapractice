package queue;

import stack.MyStack;

public class StackBasedQueue<T> {

    private final MyStack<T> leftStack;
    private final MyStack<T> rightStack;

    public StackBasedQueue() {
        leftStack = new MyStack<>();
        rightStack = new MyStack<>();
    }

    public boolean offer(T item) {
        leftStack.push(item);
        return true;
    }

    public T peek() {
        moveElementsToRight();
        return rightStack.peek();
    }

    public T poll() {
        moveElementsToRight();
        return rightStack.pop();
    }

    private void moveElementsToRight() {
        if (rightStack.isEmpty())
            while (!leftStack.isEmpty())
                rightStack.push(leftStack.pop());
    }

    public boolean isEmpty() {
        return leftStack.isEmpty() && rightStack.isEmpty();
    }

    @Override
    public String toString() {
        return "Left: %s Right: %s".formatted(leftStack, rightStack);
    }
}
