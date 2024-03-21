import graph.Graph;
import hashtable.MyHashTableLP;
import hashtable.MyHashtable;
import linkedlist.MyLinkedList;
import queue.*;
import random.FibonacciSequence;
import stack.Browser;
import stack.MinStack;
import stack.TwoStacks;
import tree.AVLTree;
import tree.AgainAVLTree;
import tree.BinarySearchTree;
import tree.Heap;

import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it
        // Press Shift+F10 or click the green arrow button in the gutter to run the code.


        var graph = new Graph<String>();
//        graph.addNode("X");
//        graph.addNode("A");
//        graph.addNode("P");
//        graph.addNode("B");
//
//        graph.addEdge("X", "A");
//        graph.addEdge("X", "B");
//        graph.addEdge("A", "P");
//        graph.addEdge("B", "P");
//        graph.addEdge("P", "X");

        //System.out.println(graph.topologicalSort());


        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "A");

        System.out.println(graph.hasCycle());


//        graph.addNode("A");
//        graph.addNode("B");
//        graph.addNode("C");
//        graph.addNode("D");
//        graph.addNode("E");
//
//        graph.addEdge("A", "B");
//        graph.addEdge("A", "E");
//
//        graph.addEdge("B", "E");
//
//        graph.addEdge("C", "A");
//        graph.addEdge("C", "B");
//        graph.addEdge("C", "D");
//
//        graph.addEdge("D", "E");


        /* graph.addNode("John");
        graph.addNode("Mary");
        graph.addNode("Alice");
        graph.addNode("Bob");

        graph.addEdge("John", "Bob");
        graph.addEdge("John", "Mary");
        graph.addEdge("John", "Alice");
        graph.addEdge("Mary", "Alice");
        graph.addEdge("Mary", "Bob");
        graph.addEdge("Alice", "Bob");

        graph.removeEdge("John", "Bob");
        graph.removeEdge("John", "Mary");
        graph.removeEdge("John", "Alice");*/

        System.out.println(graph);

        if(true) return;

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

        var heap = new Heap<Integer>(10);
        for (int i : new int[] {15, 10, 3, 8, 12, 9, 4, 1, 24}) {
            heap.insert(i);
        }
        heap.remove();
        System.out.println(heap);


    }
}