package tree;

import hashtable.MyHashtable;
import linkedlist.MyLinkedList;

import java.util.Arrays;
import java.util.Objects;

public class Trie {

    private class Node {
        char value;
        boolean endOfWord;
        Node[] children = new Node[26];

        Node() {
        }

        Node(char value) {
            this.value = value;
        }

        boolean hasChild(char ch) {
            return children[ch - 97] != null;
        }

        Node getChild(char ch) {
            return children[ch - 97];
        }

        void addChild(char ch) {
            children[ch - 97] = new Node(ch);
        }

        Node[] getChildren() {
            return Arrays.stream(children).filter(Objects::nonNull).toArray(Node[]::new);
        }

        @Override
        public String toString() {
            return "value = " + value;
        }
    }

    private Node root;

    public Trie() {
    }

    public void insert(String word) {
        if (isEmpty())
            root = new Node();
        var current = root;
        for (char ch : word.toCharArray()) {
            if (!current.hasChild(ch))
                current.addChild(ch);
            current = current.getChild(ch);
        }
        current.endOfWord = true;
    }

    public boolean contains(String word) {
        if (isEmpty())
            return false;
        var current = root;
        for (char ch : word.toCharArray()) {
            if (!root.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.endOfWord;
    }

    public MyLinkedList<Character> preOrderTraversal() {
        var list = new MyLinkedList<Character>();
        preOrderTraversal(root, list);
        return list;
    }

    private void preOrderTraversal(Node root, MyLinkedList<Character> list) {
        list.addLast(root.value);
        for (Node child : root.getChildren())
            preOrderTraversal(child, list);
    }

    public MyLinkedList<Character> postOrderTraversal() {
        var list = new MyLinkedList<Character>();
        postOrderTraversal(root, list);
        return list;
    }

    private void postOrderTraversal(Node root, MyLinkedList<Character> list) {
        for (Node child : root.getChildren())
            postOrderTraversal(child, list);
        list.addLast(root.value);
    }

    public boolean isEmpty() {
        return root == null;
    }
}
