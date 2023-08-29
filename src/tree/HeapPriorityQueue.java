package tree;

public class HeapPriorityQueue<T> {

    private final MinHeap<Integer, T> minHeap;

    public HeapPriorityQueue(int capacity) {
        this.minHeap = new MinHeap<>(capacity);
    }

    public void add(T value, int priority) {
        minHeap.insert(priority, value);
    }

    public T remove() {
        return minHeap.remove();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
}
