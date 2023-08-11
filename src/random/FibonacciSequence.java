package random;

import linkedlist.MyLinkedList;

public final class FibonacciSequence {

    private final int f1;
    private final int f2;

    public FibonacciSequence(int f1, int f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    public MyLinkedList<Integer> list(int k) {
        if (k < 3)
            throw new IllegalArgumentException();

        var list = new MyLinkedList<Integer>();

        // Iterative solution

        int f1 = this.f1;
        int f2 = this.f2;

        list.addLast(f1);
        list.addLast(f2);

        for (int i = 0; i < k - 2; i++) {
            list.addLast(f2 = f1 + f2);
            f1 = f2 - f1;
        }

        return list;

        /*var list = new MyLinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            list.addLast(getElement(i + 1));
        }
        return list;*/
    }

    public int getElement(int i) {
        if (i == 1)
            return f1;
        if (i == 2)
            return f2;
        return getElement(i - 1) + getElement(i - 2);
    }
}
