package linkedlist;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Double linked list
 *
 * @param <T>
 */
public class MyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * O(1)
     * @param item
     */
    public void addLast(T item) {
        size++;
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.previous = tail;
        tail = newNode;
    }

    /**
     * O(1)
     * @param item
     */
    public void addFirst(T item) {
        size++;
        var newNode = new Node<>(item);
        if (isEmpty()) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head.previous = newNode;
        head = newNode;
    }

    /**
     * O(1)
     */
    public T removeLast() {
        if (isEmpty())
            throw new IllegalStateException();
        if (head == tail) {
            T removed = tail.item;
            head = tail = null;
            return removed;
        }
        T removed = tail.item;
        var newTail = tail.previous;
        newTail.next = null;
        tail.previous = null; // not necessary
        tail = newTail;
        size--;
        return removed;
    }

    /**
     * O(1)
     */
    public T removeFirst() {
        if (isEmpty())
            throw new IllegalStateException();
        var item = head.item;

        if (head == tail) {
            head = tail = null;
            return item;
        }

        var newHead = head.next;
        newHead.previous = null;
        head.next = null; // not necessary
        head = newHead;
        size--;
        return item;
    }

    /**
     * O(n)
     * @param index
     * @return
     */
    public T remove(int index) {
        if (isEmpty())
            throw new IllegalStateException();

        var current = head;
        while (current != null && index-- != 0)
            current = current.next;

        if (current != null) {

            unlink(current);

            return current.item;
        }

        return null;
    }

    private void unlink(Node<T> current) {
        var previous = current.previous;
        var next = current.next;

        if (previous != null) previous.next = next;
        if (next != null) next.previous = previous;


        // not necessary
        current.previous = null;
        current.next = null;
    }

    public T remove(Object obj) {
        var current = head;
        while (current != null && !Objects.equals(current.item, obj)) {
            current = current.next;
        }
        if (current != null) {
            unlink(current);
            return current.item;
        }
        return null;
    }

    public T removeLastOccurrence(Object obj) {
        var current = tail;
        while (current != null && !Objects.equals(current.item, obj)) {
            current = current.previous;
        }
        if (current != null) {
            unlink(current);
            return current.item;
        }
        return null;
    }

    /**
     * O(n)
     */
    public void reverse() {
        var current = head;
        while (current != null) {
            var next = current.next;
            current.next = current.previous;
            current.previous = next;
            current = next;
        }
        swapHead();
    }

    private void swapHead() {
        var oldHead = head;
        head = tail;
        tail = oldHead;
    }

    public void reverseOldFashioned() {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            var next = current.next;
            current.next = prev;
            current.previous = next;
            prev = current;
            current = next;
        }
        swapHead();
    }

    /**
     * O(n)
     * @param index
     * @return
     */
    @SuppressWarnings({"DataFlowIssue"})
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        var current = head;
        while (current != null && index-- != 0)
            current = current.next;
        return current.item;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append('[');
        var current = head;
        while (current != tail) {
            sb.append(current).append(", ");
            current = current.next;
        }
        if (tail != null) sb.append(tail.item);
        sb.append(']');
        return sb.toString();
    }

    public T getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.item;
    }

    public T getLast() {
        return tail == null ? null : tail.item;
    }

    public void clear() {
        head = tail = null;
    }
    
    public void printMiddle() {
        if (isEmpty())
            throw new IllegalStateException();
        var first = head;
        var second = head;
        while (second.next != tail && second != tail) {
            first = first.next;
            second = second.next.next;
        }

        if (second == tail) {
            System.out.println(first);
        } else {
            System.out.println(first);
            System.out.println(first.next);
        }
        /*var current = head;
        int counter = 0;
        int h = size / 2;
        while (current != null) {
            if (size % 2 == 0) {
                if ((counter == h - 1 || counter == h))
                    System.out.println(current.item);
            } else {
                if (counter == size / 2)
                    System.out.println(current.item);
            }
            current = current.next;
            counter++;
        }*/
    }

    /**
     * O(n)
     * @param k
     * @return
     */
    public T getKthNodeFromEnd(int k) {
        if (k < 1) throw new NoSuchElementException();
        if (isEmpty()) throw new NoSuchElementException();

        var first = head;
        var second = head;
        while (k-- != 1) {
            second = second.next;
            if (second == null)
                throw new NoSuchElementException();
        }

        while (second != tail) {
            first = first.next;
            second = second.next;
        }

        return first.item;
    }

    public boolean hasLoop() {
        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> previous;

        public Node(T item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return Objects.toString(item);
        }
    }
}
