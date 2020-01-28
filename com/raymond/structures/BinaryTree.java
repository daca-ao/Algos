import java.util.LinkedList;
import java.util.Queue;

abstract class BinaryTree {

    BinaryTreeNode root;

    abstract void insertNode(Integer value);

    abstract boolean deleteNode(Integer value);

    abstract BinaryTreeNode search(Integer value);

    void preOrder(BinaryTreeNode node) {
        if (node != null) {
            node.printValue();
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }

    void inOrder(BinaryTreeNode node) {
        if (node != null) {
            inOrder(node.getLeftChild());
            node.printValue();
            inOrder(node.getRightChild());
        }
    }

    void posOrder(BinaryTreeNode node) {
        if (node != null) {
            posOrder(node.getLeftChild());
            posOrder(node.getRightChild());
            node.printValue();
        }
    }

    void levelOrder(BinaryTreeNode node) {
        if (node == null) {
            return;
        }
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            BinaryTreeNode curr = queue.poll();
            curr.printValue();
            if (curr.getLeftChild() != null) {
                queue.offer(curr.getLeftChild());
            }
            if (curr.getRightChild() != null) {
                queue.offer(curr.getRightChild());
            }
        }
    }

    BinaryTreeNode getRoot() {
        return root;
    }
}