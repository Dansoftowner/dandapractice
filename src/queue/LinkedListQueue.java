package queue;

import linkedlist.MyLinkedList;

public class LinkedListQueue<T> {

    private final MyLinkedList<T> data;

    public LinkedListQueue() {
        data = new MyLinkedList<>();
    }

    public boolean offer(T item) {
        data.addLast(item);
        return true;
    }

    public T peek() {
        return data.getFirst();
    }

    public T poll() {
        return data.removeFirst();
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
