package array;

import java.util.NoSuchElementException;
import java.util.Objects;

public class MyArrayList<T> {
    private T[] data;
    private int pointer;

    public MyArrayList(int initialCapacity) {
        data = (T[]) new Object[initialCapacity];
    }

    public T get(int index) {
        return data[index];
    }

    public int indexOf(T item) {
        for (int i = 0; i < pointer; i++) {
            if (Objects.equals(item, get(i))) {
                return i;
            }
        }
        return -1;
    }

    public void add(T item)  {
        if (pointer >= data.length) {
            T[] newData = arrayCopy(data, data.length * 2);
            //System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[pointer++] = item;
    }

    public void removeLast() {
        data[pointer--] = null;
    }

    public void removeAt(int index) {
        if (index >= pointer)
            throw new IndexOutOfBoundsException();
        data[index] = null;
        for (int i = index + 1; i < pointer; i++)
            data[i - 1] = data[i];
        pointer--;
    }

    public void remove(T item) {
        removeAt(indexOf(item));
    }

    private T[] arrayCopy(T[] old, int newLength) {
        if (old.length > newLength)
            throw new IllegalArgumentException();
        T[] newArray = (T[]) new Object[newLength];
        for (int i = 0; i < old.length; i++) {
            newArray[i] = old[i];
        }
        return newArray;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < pointer; i++) {
            sb.append(data[i]);
            if (i != (pointer -1))
                sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
}
