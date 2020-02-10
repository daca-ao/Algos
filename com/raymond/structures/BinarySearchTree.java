/**
 * Binary Search Tree without duplicate elements.
 */
public class BinarySearchTree<T extends Comparable> extends AbstractBinarySearchTree<T> {

    @Override
    public void insertNode(T value) {
        if (value == null) {
            System.err.println("Please do not insert a null value.");
            return;
        }
        if (root == null) {
            root = new BinaryTreeNode<>(value);
            return;
        }
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        BinaryTreeNode<T> curr = root;
        while (true) {
            BinaryTreeNode<T> parent = curr;
            if (value.compareTo(curr.getValue()) > 0) {
                curr = curr.getRightChild();
                if (curr == null) {
                    parent.setRightChild(node);
                    return;
                }
            } else if (value.compareTo(curr.getValue()) < 0) {
                curr = curr.getLeftChild();
                if (curr == null) {
                    parent.setLeftChild(node);
                    return;
                }
            } else {
                System.err.println("Node " + value + " already there.");
                return;
            }
        }
    }

    @Override
    public boolean deleteNode(T value) {
        if (value == null) {
            System.out.println("No operation for null value.");
            return false;
        }
        if (root == null) {
            System.err.println("Tree null. Deletion failed.");
            return false;
        }

        // search out the node
        BinaryTreeNode<T> curr = root;
        BinaryTreeNode<T> parent = root;
        boolean isInLeft = true;
        while (value != curr.getValue()) {
            isInLeft = value.compareTo(curr.getValue()) < 0;
            parent = curr;
            curr = value.compareTo(curr.getValue()) > 0 ? curr.getRightChild() : curr.getLeftChild();
            if (curr == null) {
                System.err.println("Element not found.");
                return false;
            }
        }

        // reconstruct the tree
        if (curr.getLeftChild() == null && curr.getRightChild() == null) {
            System.out.println("Deleting leaf " + value);
            if (isInLeft) {
                parent.setLeftChild(null);
            } else {
                parent.setRightChild(null);
            }
            return true;
        }
        if (curr.getLeftChild() == null || curr.getRightChild() == null) {
            System.out.println(
                    "Deleting node " + value + " with " + (curr.getLeftChild() != null ? "left" : "right") + " child.");
            if (isInLeft) {
                parent.setLeftChild(curr.getLeftChild() == null ? curr.getRightChild() : curr.getLeftChild());
            } else {
                parent.setRightChild(curr.getLeftChild() == null ? curr.getRightChild() : curr.getLeftChild());
            }
            return true;
        }
        System.out.println("Deleting node " + value + " with children.");
        BinaryTreeNode<T> predecessor = getPredecessorTree(curr);
        if (root == curr) {
            root = predecessor;
        } else if (isInLeft) {
            parent.setLeftChild(predecessor);
        } else {
            parent.setRightChild(predecessor);
        }
        predecessor.setRightChild(curr.getRightChild());
        return true;
    }

}