package queue;

import stack.MyStack;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    private Object[] data;

    private int size;
    private int front;
    private int end = -1;

    public MyQueue(int initialCapacity) {
        data = new Object[initialCapacity];
    }

    public boolean offer(T item) {
        if (isFull())
            grow();
        end = (end + 1) % data.length;
        data[end] = item;
        size++;
        return true;
    }

    private void grow() {
        var newStore = new Object[data.length * 2];
        for (int i = 0; i < size; i++)
            newStore[i] = data[(front + i) % data.length];
        front = 0;
        end = size - 1;
        data = newStore;
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) throw new NoSuchElementException();
        T item = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public void reverse() {
        var s = new MyStack<T>();
        while (!isEmpty())
            s.push(poll());
        while(!s.isEmpty())
            offer(s.pop());
    }

    public int getSize() {
        return size;
    }


    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
