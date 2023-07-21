package stack;

import java.util.NoSuchElementException;

public class TwoStacks<T> {

    private Object[] data;

    private int size1;
    private int size2;

    public TwoStacks(int capacity) {
        data = new Object[capacity];
    }

    public T push1(T item) {
        if (isFull1()) throw new IllegalStateException("The stack (1) is full");
        data[size1++] = item;
        return item;
    }

    public T push2(T item) {
        if (isFull2()) throw new IllegalStateException("The stack (2) is full");
        data[data.length - ++size2] = item;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T pop1() {
        if (isEmpty1()) throw new NoSuchElementException("The stack (1) is empty");
        size1--;
        var peek = data[size1];
        data[size1] = null;
        return (T) peek;
    }

    @SuppressWarnings("unchecked")
    public T pop2() {
        // [A,,,B,A]
        if (isEmpty2()) throw new NoSuchElementException("The stack (2) is empty");
        var peek = data[data.length - size2];
        data[data.length - size2] = null;
        size2--;
        return (T) peek;
    }

    public boolean isEmpty1() {
        return size1 == 0;
    }

    public boolean isEmpty2() {
        return size2 == 0;
    }

    public boolean isFull1() {
        return (data.length - size2) == size1;
    }

    public boolean isFull2() {
        return (data.length - size1) == size2;
    }

    private String stack1String() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size1; i++) {
            sb.append(data[i]);
            if (i != size1 - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private String stack2String() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 1; i <= size2; i++) {
            sb.append(data[data.length - i]);
            if (i != size2) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack1: ");
        sb.append(stack1String());
        sb.append("\n");
        sb.append("Stack2: ");
        sb.append(stack2String());
        sb.append("\n");
        return sb.toString();
    }


}
