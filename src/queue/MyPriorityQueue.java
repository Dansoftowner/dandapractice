package queue;

import java.util.Arrays;
import java.util.Objects;

public class MyPriorityQueue<T extends Comparable<T>> {

    private Object[] data;
    private int size;

    public MyPriorityQueue(int initialCapacity) {
        data = new Object[initialCapacity];
    }

    @SuppressWarnings("unchecked")
    public boolean offer(T item) {
        Objects.requireNonNull(item);
        if (isFull())
            data = Arrays.copyOf(data, data.length * 2);
        int i = size -1;
        while (i >= 0 && priorTo((T) data[i], item)) {
            data[i + 1] = data[i];
            i--;
        }
        data[i + 1] = item;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        return (T) data[size -1];
    }

    public T poll() {
        if (isEmpty())
            throw new IllegalStateException();
        T peek = peek();
        data[size--] = null;
        return peek;
    }

    public boolean isFull() {
        return data.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean priorTo(T a, T b) {
        return a.compareTo(b) < 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(data[i]);
            if (i != 0)
                sb.append(", ");
        }
        return sb.toString();
    }
}
