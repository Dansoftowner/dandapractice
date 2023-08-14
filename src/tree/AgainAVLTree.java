package tree;

import array.MyArrayList;

import java.util.function.Consumer;

public class AgainAVLTree<T extends Comparable<T>> {

    private class AVLNode {
        private T value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        AVLNode(T value) {
            this.value = value;
        }
    }

    private AVLNode root;

    public void insert(T value) {
        root = insert(root, value);
    }

    private AVLNode insert(AVLNode root, T value) {
        if (root == null)
            return new AVLNode(value);

        if (Comparables.lessThan(root.value, value))
            root.rightChild = insert(root.rightChild, value);
        else
            root.leftChild = insert(root.leftChild, value);

        updateHeight(root);
        return balance(root);
    }

    private AVLNode balance(AVLNode root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.leftChild) < 0)
                root.leftChild = leftRotate(root.leftChild);
            return rightRotate(root);
        } else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
                root.rightChild = rightRotate(root.rightChild);
            return leftRotate(root);
        }
        return root;
    }

    private AVLNode leftRotate(AVLNode root) {
        var newRoot = root.rightChild;
        root.rightChild = newRoot.leftChild;
        newRoot.leftChild = root;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }

    private AVLNode rightRotate(AVLNode root) {
        var newRoot = root.leftChild;
        root.leftChild = newRoot.rightChild;
        newRoot.rightChild = root;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }

    private int balanceFactor(AVLNode root) {
        return height(root.leftChild) - height(root.rightChild);
    }

    private boolean isLeftHeavy(AVLNode root) {
        return balanceFactor(root) > 1;
    }

    private boolean isRightHeavy(AVLNode root) {
        return balanceFactor(root) < -1;
    }

    private void updateHeight(AVLNode root) {
        root.height = 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    private int height(AVLNode root) {
        return root == null ? -1 : root.height;
    }

    public boolean equals(AgainAVLTree<T> tree) {
        return equals(root, tree.root);
    }

    private boolean equals(AVLNode root1, AVLNode root2) {
        if (root1 != null && root2 != null)
            return root1.value == root2.value &&
                    equals(root1.leftChild, root2.leftChild) &&
                    equals(root1.rightChild, root2.rightChild);
        return root1 == root2;
    }

    private void elementsAtDistance(AVLNode root, int k, Consumer<T> consumer) {
        if (root == null)
            return;
        if (k == 0)
            consumer.accept(root.value);
        elementsAtDistance(root.leftChild, k - 1, consumer);
        elementsAtDistance(root.rightChild, k - 1, consumer);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode root) {
        if (root == null)
            return true;

        boolean isBalanced = Math.abs(balanceFactor(root)) <= 1;
        return isBalanced && isBalanced(root.leftChild) && isBalanced(root.rightChild);
    }

    public int size() {
        return size(root);
    }

    private int size(AVLNode root) {
        if (root == null)
            return 0;
        return 1 + size(root.leftChild) + size(root.rightChild);
    }

    public boolean isPerfect() {
        int size = size();
        return size == Math.pow(2, height(root) + 1) - 1;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int k = 0; k <= height(root); k++) {
            elementsAtDistance(root, k, value -> sb.append(value).append(" "));
            sb.append("\n----------------\n");
        }
        return sb.toString();
    }
}
