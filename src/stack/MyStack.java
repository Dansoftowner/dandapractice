package stack;

import linkedlist.MyLinkedList;

public class MyStack<T> {

    private MyLinkedList<T> list;

    public MyStack() {
        list = new MyLinkedList<>();
    }

    public T push(T item) {
        list.addLast(item);
        return item;
    }

    public T pop() {
        return list.removeLast();
    }

    public T peek() {
        return list.getLast();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
