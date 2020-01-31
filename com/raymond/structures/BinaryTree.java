import java.util.LinkedList;
import java.util.Queue;

abstract class BinaryTree<T extends Comparable> {

    BinaryTreeNode<T> root;

    abstract void insertNode(T value);

    abstract boolean deleteNode(T value);

    abstract BinaryTreeNode<T> search(T value);

    void preOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            node.printValue();
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    void inOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            node.printValue();
            inOrder(node.getRightChild());
        }
    }

    void posOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            posOrder(node.getLeftChild());
            posOrder(node.getRightChild());
            node.printValue();
        }
    }

    void levelOrder(BinaryTreeNode<T> node) {
        if (node == null) {
            return;
        }
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> curr = queue.poll();
            curr.printValue();
            if (curr.getLeftChild() != null) {
                queue.offer(curr.getLeftChild());
            }
            if (curr.getRightChild() != null) {
                queue.offer(curr.getRightChild());
            }
        }
    }

    BinaryTreeNode<T> getRoot() {
        return root;
    }
}