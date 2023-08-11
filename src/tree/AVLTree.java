package tree;

import array.MyArrayList;

import java.util.Objects;

import static tree.Comparables.lessThan;

public class AVLTree<T extends Comparable<T>> {

    private static class AVLNode<T extends Comparable<T>> {

        private T item;
        private AVLNode<T> leftChild;
        private AVLNode<T> rightChild;

        private int height;

        AVLNode(T item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return Objects.toString(item);
        }
    }

    private AVLNode<T> root;

    public AVLTree() {
    }

    public void insert(T item) {
        root = insert(root, new AVLNode<>(item));
    }

    private AVLNode<T> insert(AVLNode<T> root, AVLNode<T> newNode) {
        if (root == null)
            return newNode;
        if (lessThan(root.item, newNode.item))
            root.rightChild = insert(root.rightChild, newNode);
        else
            root.leftChild = insert(root.leftChild, newNode);

        recalculateHeight(root);
        return balance(root);
    }

    private AVLNode<T> balance(AVLNode<T> root) {
        if (isLeftHeavy(root)) {
            if (balanceFactor(root.leftChild) < 0)
                leftRotate(root.leftChild);
            return rightRotate(root);
        }
        else if (isRightHeavy(root)) {
            if (balanceFactor(root.rightChild) > 0)
                rightRotate(root.rightChild);
            return leftRotate(root);
        }
        return root;
    }

    private AVLNode<T> leftRotate(AVLNode<T> node) {
        AVLNode<T> rightChild = node.rightChild;

        var temp = rightChild.leftChild;
        rightChild.leftChild = node;
        node.rightChild = temp;

        recalculateHeight(node);
        recalculateHeight(rightChild);

        return rightChild;
    }

    private AVLNode<T> rightRotate(AVLNode<T> node) {
        AVLNode<T> leftChild = node.leftChild;

        var temp = leftChild.rightChild;
        node.leftChild.rightChild = node;
        node.leftChild = temp;

        recalculateHeight(node);
        recalculateHeight(leftChild);

        return leftChild;
    }

    public MyArrayList<T> levelOrder() {
        var list = new MyArrayList<T>((int) Math.pow(2, root.height + 1) - 1);
        for (int i = 0; i <= height(root); i++) {
            getElementsAtDistance(root, i, list);
        }
        return list;
    }

    private void getElementsAtDistance(AVLNode<T> root, int k, MyArrayList<T> list) {
        if (root == null)
            return;
        if (k == 0) {
            list.add(root.item);
            return;
        }
        getElementsAtDistance(root.leftChild, k - 1, list);
        getElementsAtDistance(root.rightChild, k - 1, list);
    }


    private boolean isLeftHeavy(AVLNode<T> node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode<T> node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode<T> node) {
        return height(node.leftChild) - height(node.rightChild);
    }

    private void recalculateHeight(AVLNode<T> node) {
        node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
    }

    private int height(AVLNode<T> node) {
        return node == null ? -1 : node.height;
    }

    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i <= height(root); i++) {
            var list = new MyArrayList<T>((int) Math.pow(2, i));
            getElementsAtDistance(root, i, list);
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j));
                sb.append(" ");
            }
            sb.append("\n");
            sb.append("-----------------------------");
            sb.append("\n");
        }
        return sb.toString();
    }

}
