import hashtable.MyHashTableLP;
import hashtable.MyHashtable;
import linkedlist.MyLinkedList;
import queue.*;
import random.FibonacciSequence;
import stack.Browser;
import stack.MinStack;
import stack.TwoStacks;
import tree.AVLTree;
import tree.BinarySearchTree;

import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it
        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        var list = new MyLinkedList<>();
        var ms = new MinStack<Integer>();
        Stream.of(5, 2, 10, 1).forEach(ms::push);
        var twoStacks = new TwoStacks<Character>(5);
        for (int letter = 0; letter < 7; letter++) {
            list.addLast((char) (65 + letter));
        }

        var queue = new MyQueue<>(5);
        var prQueue = new MyPriorityQueue<Integer>(5);
        var sbQ = new StackBasedQueue<>();
        var lq = new LinkedListQueue<>();
        var qSt = new QueueBasedStack<>();
        var browser = new Browser();

        var map = new MyHashtable<>(10);
        var lpMap = new MyHashTableLP<Integer, Integer>(5);

        var binarySearchTree = new BinarySearchTree<Integer>();
        for (var i : new int[] { 7, 9, 4, 6, 1, 10, 8}) binarySearchTree.insert(i);
        System.out.println(binarySearchTree.inOrderTraversal());
        System.out.println(list);

        var avl = new AVLTree<Integer>();
        avl.insert(20);
        avl.insert(10);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        System.out.println(avl);
        System.out.println(new FibonacciSequence(1, 1).list(10));

    }
}