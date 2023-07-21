package queue;

import stack.MyStack;

public class QueueReverser {

    private QueueReverser() {
    }

    public static <T> void reverse(MyQueue<T> queue, int k) {
        if (queue.getSize() < k)
            throw new IllegalArgumentException();

        MyStack<T> stack = new MyStack<>();
        int j = k;
        while (j-- > 0) stack.push(queue.poll());
        while(!stack.isEmpty()) queue.offer(stack.pop());
        j = queue.getSize() - k;
        while (j-- > 0) queue.offer(queue.poll());
    }
}
