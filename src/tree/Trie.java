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

        void removeChild(char ch) {
            children[ch - 97] = null;
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
            if (!current.hasChild(ch))
                return false;
            current = current.getChild(ch);
        }
        return current.endOfWord;
    }

    public void remove(String word) {
        remove(root, word, 0);
    }

    private boolean remove(Node root, String word, int i) {
        if (i >= word.length())
            return true;
        char ch = word.charAt(i);
        if (root.hasChild(ch)) {
            if (remove(root.getChild(ch), word, i + 1)) {
                var child = root.getChild(ch);
                child.endOfWord = false;
                if (child.getChildren().length == 0) {
                    root.removeChild(ch);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
