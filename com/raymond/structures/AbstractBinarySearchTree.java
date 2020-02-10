abstract class AbstractBinarySearchTree<T extends Comparable> extends AbstractBinaryTree<T> {

    @Override
    BinaryTreeNode<T> search(T value) {
        if (value == null) {
            System.err.println("Value " + value + " not exist.");
            return null;
        }
        if (root == null) {
            System.err.println("Empty root. Tree has not been initialized.");
            return null;
        }
        BinaryTreeNode<T> curr = root;
        while (value != curr.getValue()) {
            curr = value.compareTo(curr.getValue()) > 0 ? curr.getRightChild() : curr.getLeftChild();
            if (curr == null) {
                return null;
            }
        }
        return curr;
    }

    // Not only get the predecessor
    BinaryTreeNode<T> getPredecessorTree(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode<T> predParent = node;
        BinaryTreeNode<T> predecessor = node;
        BinaryTreeNode<T> curr = node.getLeftChild();
        while (curr != null) {
            predParent = predecessor;
            predecessor = curr;
            curr = curr.getRightChild();
        }
        // if predecessor is in right child of the node's left child,<br/>
        // not the node's left child:
        if (predecessor != node.getLeftChild()) {
            predParent.setRightChild(predecessor.getLeftChild());
            predecessor.setLeftChild(node.getLeftChild());
        }
        return predecessor;
    }

    BinaryTreeNode<T> getPredecessor(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode<T> predecessor = node;
        BinaryTreeNode<T> curr = node.getLeftChild();
        while (curr != null) {
            predecessor = curr;
            curr = curr.getRightChild();
        }
        return predecessor;
    }

    BinaryTreeNode<T> getSuccessor(BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }
        BinaryTreeNode<T> successor = node;
        BinaryTreeNode<T> curr = node.getRightChild();
        while (curr != null) {
            successor = curr;
            curr = curr.getLeftChild();
        }
        return successor;
    }

}