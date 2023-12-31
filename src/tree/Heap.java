package tree;

public class Heap<T extends Comparable<T>> {

    private T[] tree;
    private int size;

    @SuppressWarnings("unchecked")
    public Heap(int capacity) {
        tree = (T[]) new Comparable[capacity];
    }

    public void insert(T value) {
        if (isFull())
            throw new IllegalStateException();
        tree[size++] = value;
        bubbleUp();
    }

    public T remove() {
        if (isEmpty())
            throw new IllegalStateException();
        var removed = tree[0];
        tree[0] = tree[size - 1];
        tree[--size] = null;
        bubbleDown();
        return removed;
    }

    private void bubbleUp() {
        int i = size - 1;
        while (tree[i].compareTo(parent(i)) > 0) {
            int parentIndex = parentIndex(i);
            swap(i, parentIndex);
            i = parentIndex;
        }
    }

    private void bubbleDown() {
        int i = 0;
        while(!isValidParent(i)) {
            int greaterChildIndex = greaterChildIndex(i);
            swap(i, greaterChildIndex);
            i = greaterChildIndex;
        }
    }

    private boolean isValidParent(int index) {
        if (greaterChildIndex(index) >= 0)
            return greaterChild(index).compareTo(tree[index]) < 0;
        return true;
    }

    private T greaterChild(int index) {
        return tree[greaterChildIndex(index)];
    }

    private int greaterChildIndex(int i) {
        if (!hasLeftChild(i))
            return -1;
        if (!hasRightChild(i))
            return leftChildIndex(i);
        return leftChild(i).compareTo(rightChild(i)) > 0 ? leftChildIndex(i) : rightChildIndex(i);
    }

    private boolean hasLeftChild(int index) {
        return index * 2 + 1 < size;
    }

    private boolean hasRightChild(int index) {
        return index * 2 + 2 < size;
    }

    private T rightChild(int index) {
        return tree[rightChildIndex(index)];
    }

    private T leftChild(int index) {
        return tree[leftChildIndex(index)];
    }

    private T parent(int index) {
        return tree[parentIndex(index)];
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = tree[index1];
        tree[index1] = tree[index2];
        tree[index2] = temp;
    }

    public boolean isFull() {
        return tree.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        int k = 0;

        mainLoop:
        for (int itemsCount = 1; ; itemsCount *= 2) {
            for (int i = 0; i < itemsCount; i++) {
                if (k + i >= size)
                    break mainLoop;
                sb.append(tree[k + i]);
                sb.append(" ");
            }
            k += itemsCount;
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Heap Sort
     * O(n)
     */
    public static <T extends Comparable<T>> void sort(T[] array) {
        var heap = new Heap<T>(array.length);
        for (T item : array) heap.insert(item);
        for (int i = array.length - 1; i >= 0; i--)
            array[i] = heap.remove();
    }

    public static int kthLargestItem(int[] array, int k) {
        if (k < 1 || k > array.length)
            throw new IllegalArgumentException();
        var heap = new Heap<Integer>(array.length);
        for (int i : array) heap.insert(i);
        for (int i = 1; i < k; i++) heap.remove();
        return heap.remove();
    }

    public static boolean isMaxHeap(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int leftChildIndex = i * 2 + 1;
            int rightChildIndex = i * 2 + 2;
            if (leftChildIndex < array.length && array[leftChildIndex] > array[i])
                return false;
            if (rightChildIndex < array.length && array[rightChildIndex] > array[i])
                return false;
        }
        return true;
    }

    public static boolean isMaxHeap2(int[] array) {
        return isMaxHeap2(array, 0);
    }

    private static boolean isMaxHeap2(int[] array, int i) {
        int lastParentIndex = array.length / 2 - 1;
        if (i > lastParentIndex)
            return true;
        int leftChildIndex = i * 2 + 1;
        int rightChildIndex = i * 2 + 2;
        boolean isValid = array[leftChildIndex] <= array[i] && (rightChildIndex >= array.length || array[rightChildIndex] <= array[i]);
        return isValid && isMaxHeap2(array, leftChildIndex) && isMaxHeap2(array, rightChildIndex);
    }
}
