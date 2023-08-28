package tree;

public class Heapify {
    private Heapify() {
    }

    public static void heapify(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i);
        }
    }

    private static void heapify(int[] array, int index) {
        int largestIndex = index;
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if (leftChildIndex < array.length && array[leftChildIndex] > array[largestIndex]) largestIndex = leftChildIndex;
        if (rightChildIndex < array.length && array[rightChildIndex] > array[largestIndex])
            largestIndex = rightChildIndex;

        if (largestIndex == index)
            return;
        swap(array, index, largestIndex);
        heapify(array, largestIndex);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
