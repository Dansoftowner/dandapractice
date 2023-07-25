package tree;

import array.MyArrayList;
import linkedlist.MyLinkedList;

import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node {
        T value;
        Node left;
        Node right;

        Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    private Node root;

    public void insert(T item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        while(true) {
            if (lessThan(current, newNode)) {
                if (current.right == null) {
                    current.right = newNode;
                    return;
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                }
                current = current.left;
            }

        }
    }

    public boolean contains(T value) {
        Node current = root;
        while (current != null) {
            if (current.value.equals(value))
                return true;
            if (lessThan(current.value, value))
                current = current.right;
            else
                current = current.left;
        }
        return false;
    }

    public MyLinkedList<T> inOrderTraversal() {
        var list = new MyLinkedList<T>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node root, MyLinkedList<T> list) {
        if (root == null)
            return;

        inOrderTraversal(root.left, list);
        list.addLast(root.value);
        inOrderTraversal(root.right, list);
    }

    public MyLinkedList<T> preOrderTraversal() {
        var list = new MyLinkedList<T>();
        preOrderTraversal(root, list);
        return list;
    }

    private void preOrderTraversal(Node root, MyLinkedList<T> list) {
        if (root == null)
            return;

        list.addLast(root.value);
        preOrderTraversal(root.left, list);
        preOrderTraversal(root.right, list);
    }

    public MyLinkedList<T> postOrderTraversal() {
        var list = new MyLinkedList<T>();
        postOrderTraversal(root, list);
        return list;
    }

    private void postOrderTraversal(Node root, MyLinkedList<T> list) {
        if (root == null)
            return;

        postOrderTraversal(root.left, list);
        postOrderTraversal(root.right, list);
        list.addLast(root.value);
    }

    // Breadth-first traversal - O(n)
    public MyArrayList<T> levelOrderTraversal() {
        int height = height(root);
        int maxElements = (int) Math.pow(2, height + 1) - 1;

        var list = new MyArrayList<T>(maxElements);
        for (int i = 0; i <= height; i++)
            nodesAtKDistance(root, i, list);
        return list;
    }

    public MyArrayList<T> nodesAtKDistance(int k) {
        var list = new MyArrayList<T>((int) Math.pow(2, k));
        nodesAtKDistance(root, k, list);
        return list;
    }

    private void nodesAtKDistance(Node node, int k, MyArrayList<T> list) {
        if (k < 0)
            throw new IllegalArgumentException();
        if (node == null)
            return;
        if (k == 0) {
            list.add(node.value);
            return;
        }
        nodesAtKDistance(node.left, k - 1, list);
        nodesAtKDistance(node.right, k - 1, list);
    }

    private int height(Node node) {
        // base condition
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int depth(T value) {
        Node current = root;
        int depth = 0;
        while(current != null) {
            if (current.value.equals(value)) {
                return depth;
            }
            if (lessThan(current.value, value))
                current = current.right;
            else
                current = current.left;
            depth++;
        }
        return -1;
    }

    // O(log n) - if balanced
    public T min() {
        if (isEmpty()) throw new IllegalStateException();
        var current = root;
        while (current.left != null)
            current = current.left;
        return current.value;
    }

    public T minIfNotSearchTree() {
        // we suspect that the tree is not a binary search tree
        return minIfNotSearchTree(root);
    }

    // O(n)
    private T minIfNotSearchTree(Node root) {
        if (root == null)
            return null;
        if (isLeaf(root))
            return root.value;

        T minLeft = minIfNotSearchTree(root.left);
        T minRight = minIfNotSearchTree(root.right);

        return min(root.value, min(minLeft, minRight));
    }

    public boolean equals(BinarySearchTree<T> other) {
        if (other == null) return false;
        return equals(root, other.root);
    }

    // O(n)
    private boolean equals(Node nodeOne, Node nodeTwo) {
        if (nodeOne == null && nodeTwo == null)
            return true;
        if (nodeOne == null || nodeTwo == null)
            return false;
        if (!nodeOne.value.equals(nodeTwo.value))
            return false;

        return equals(nodeOne.left, nodeTwo.left) && equals(nodeOne.right, nodeTwo.right);
    }

    public boolean isBinarySearchTree() {
        return isBinarySearchTree(root, a -> -1, a -> 1);
    }

    private boolean isBinarySearchTree(Node node, Comparable<T> min, Comparable<T> max) {
        if (node == null)
            return true;

        return min.compareTo(node.value) < 0 && max.compareTo(node.value) > 0 &&
                isBinarySearchTree(node.left, min, node.value) &&
                isBinarySearchTree(node.right, node.value, max);
    }

    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;
        return 1 + size(root.left) + size(root.right);
    }

    public boolean isBalanced() {
        return Math.abs(height(root.left) - height(root.right)) < 2;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    private T min(T a, T b) {
        if (a == null && b == null)
            throw new IllegalArgumentException();

        if (a == null)
            return b;
        else if (b == null)
            return a;

        return lessThan(a, b) ? a : b;
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private boolean lessThan(Node a, Node b) {
        return lessThan(a.value, b.value);
    }

    private boolean lessThan(T a, T b) {
        return a.compareTo(b) < 0;
    }

    private boolean greaterThan(Node a, Node b) {
        return greaterThan(a.value, b.value);
    }

    private boolean greaterThan(T a, T b) {
        return a.compareTo(b) > 0;
    }
}
