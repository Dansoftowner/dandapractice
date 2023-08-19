package tree;

import java.util.Arrays;

import static tree.Comparables.greaterThan;

public class Heap<T extends Comparable<T>> {

    private T[] tree;
    private int pointer;

    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        tree = (T[]) new Comparable[capacity];
    }

    public void insert(T value) {
        if (isFull())
            throw new IllegalStateException();
        tree[pointer] = value;
        bubbleUpIteratively(pointer);
        pointer++;
    }

    public T remove() {
        if (isEmpty())
            throw new IllegalStateException();
        var removed = tree[0];
        tree[0] = tree[pointer - 1];
        tree[--pointer] = null;
        bubbleDownIteratively(0);
        return removed;
    }

    private void bubbleUp(int index) {
        if (index == 0)
            return;
        if (tree[index].compareTo(getParent(index)) < 0)
            return;
        int parentIndex = getParentIndex(index);
        swap(index, parentIndex);
        bubbleUp(parentIndex);
    }

    private void bubbleDown(int index) {
        if (index == pointer - 1)
            return;
        if (tree[index].compareTo(getGreaterChild(index)) > 0)
            return;
        int childIndex = getGreaterChildIndex(index);
        swap(index, childIndex);
        bubbleDown(childIndex);
    }

    private void bubbleUpIteratively(int index) {
        int i = index;
        while (tree[i].compareTo(getParent(i)) > 0) {
            int parentIndex = getParentIndex(i);
            swap(i, parentIndex);
            i = parentIndex;
        }
    }

    private void bubbleDownIteratively(int index) {
        int i = index;
        while(tree[i].compareTo(getGreaterChild(i)) < 0) {
            int greaterChildIndex = getGreaterChildIndex(i);
            swap(i, greaterChildIndex);
            i = greaterChildIndex;
        }
    }

    private T getGreaterChild(int index) {
        return tree[getGreaterChildIndex(index)];
    }

    private int getGreaterChildIndex(int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        if (isOutOfScope(left))
            return index;
        if (isOutOfScope(right))
            return left;
        return tree[left].compareTo(tree[right]) > 0 ? left : right;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private T getParent(int index) {
        return tree[getParentIndex(index)];
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = tree[index1];
        tree[index1] = tree[index2];
        tree[index2] = temp;
    }

    private boolean isOutOfScope(int index) {
        return index >= pointer;
    }

    public boolean isFull() {
        return tree.length == pointer;
    }

    public boolean isEmpty() {
        return pointer == 0;
    }

    private boolean isPowerOfTwo(int number) {
        return (number & (number - 1)) == 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        int k = 0;

        mainLoop:
        for (int itemsCount = 1; ; itemsCount *= 2) {
            for (int i = 0; i < itemsCount; i++) {
                if (k + i >= pointer)
                    break mainLoop;
                sb.append(tree[k + i]);
                sb.append(" ");
            }
            k += itemsCount;
            sb.append("\n");
        }

        return sb.toString();
    }
}
