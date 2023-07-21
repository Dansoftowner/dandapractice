package queue;

public class QueueBasedStack<T> {

    private MyQueue<T> queue1;
    private MyQueue<T> queue2;
    private T top;

    public QueueBasedStack() {
        queue1 = new MyQueue<>(10);
        queue2 = new MyQueue<>(10);
    }

    // O(1)
    public boolean push(T item) {
        queue1.offer(item);
        top = item;
        return true;
    }

    // O(n)
    public T peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return top;
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException();

        while(queue1.getSize() > 1)
            queue2.offer(top = queue1.poll());

        var peek = queue1.poll();
        swapQueues();
        return peek;
    }

    public int size() {
        return queue1.isEmpty() ? queue2.getSize() : queue1.getSize();
    }

    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    private void swapQueues() {
        var temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
}
