package tree;

import hashtable.MyHashtable;

public class Trie {

    private class Node {
        char value;
        boolean endOfWord;
        MyHashtable<Character, Node> children = new MyHashtable<>(5);

        Node() {
        }

        Node(char value) {
            this.value = value;
        }

        boolean hasChild(char ch) {
            return children.get(ch) == null;
        }

        Node getChild(char ch) {
            return children.get(ch);
        }

        void addChild(char ch) {
            children.put(ch, new Node(ch));
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

    public boolean isEmpty() {
        return root == null;
    }
}
