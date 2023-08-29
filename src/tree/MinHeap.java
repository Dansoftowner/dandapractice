package tree;

public class MinHeap<K extends Comparable<K>, V> {
    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "%s=%s".formatted(key, value);
        }
    }

    private Node<K, V>[] tree;
    private int size;

    public MinHeap(int capacity) {
        this.tree = new Node[capacity];
    }

    public void insert(K key, V value) {
        if (isFull())
            throw new IllegalStateException();
        tree[size++] = new Node<>(key, value);
        bubbleUp();
    }

    public V remove() {
        if (isEmpty())
            throw new IllegalStateException();
        var removed = tree[0];
        tree[0] = tree[--size];
        tree[size - 1] = null;
        bubbleDown();
        return removed.value;
    }

    private void bubbleUp() {
        int i = size - 1;
        while(key(i).compareTo(parentKey(i)) < 0) {
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    private void bubbleDown() {
        int i = 0;
        while (!isValidParent(i)) {
            swap(i, lesserChildIndex(i));
            i = lesserChildIndex(i);
        }
    }

    public V min() {
        if (isEmpty())
            throw new IllegalStateException();
        return tree[0].value;
    }

    private boolean isValidParent(int index) {
        return isLeaf(index) || key(index).compareTo(lesserChildKey(index)) < 0;
    }

    private boolean isLeaf(int index) {
        return !hasLeftChild(index);
    }

    private K lesserChildKey(int index) {
        return tree[lesserChildIndex(index)].key;
    }

    private int lesserChildIndex(int index) {
        if (isLeaf(index))
            return -1;
        if (!hasRightChild(index))
            return leftChildIndex(index);
        return leftChildKey(index).compareTo(rightChildKey(index)) < 0 ?
                leftChildIndex(index) : rightChildIndex(index);
    }

    private boolean hasLeftChild(int index) {
        return leftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return rightChildIndex(index) < size;
    }

    private K leftChildKey(int index) {
        return tree[leftChildIndex(index)].key;
    }

    private K rightChildKey(int index) {
        return tree[rightChildIndex(index)].key;
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private void swap(int i, int j) {
        var temp = tree[i];
        tree[i] = tree[j];
        tree[j] = temp;
    }

    private K key(int index) {
        return tree[index].key;
    }

    private K parentKey(int index) {
        return tree[parentIndex(index)].key;
    }
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return tree.length == size;
    }

}
