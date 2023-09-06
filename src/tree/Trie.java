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

        boolean hasChildren() {
            return getChildren().length > 0;
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

    private void remove(Node root, String word, int i) {
        if (i == word.length()) {
            root.endOfWord = false;
            return;
        }

        char ch = word.charAt(i);
        var child = root.getChild(ch);
        if (child == null)
            return;

        remove(root, word, i + 1);
        if (!child.hasChildren() && !child.endOfWord)
            root.removeChild(ch);
    }

    public MyLinkedList<String> wordsWithPrefix(String prefix) {
        var list = new MyLinkedList<String>();
        wordsWithPrefix(findLastNodeOf(prefix), prefix, list);
        return list;
    }

    private void wordsWithPrefix(Node root, String word, MyLinkedList<String> words) {
        if (root == null)
            return;
        if (root.endOfWord)
            words.addLast(word);
        for (Node child : root.getChildren())
            wordsWithPrefix(child, word + child.value, words);
    }

    private Node findLastNodeOf(String word) {
        var current = root;
        for (char c : word.toCharArray()) {
            if (!current.hasChild(c))
                return null;
            current = current.getChild(c);
        }
        return current;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return wordsWithPrefix("").toString();
    }
}
