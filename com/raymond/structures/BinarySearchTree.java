/**
 * Tree without duplicate elements.
 */
public class BinarySearchTree extends BinaryTree {

    @Override
    public void insertNode(Integer value) {
        if (value == null) {
            System.err.println("Please do not insert a null value.");
            return;
        }
        if (root == null) {
            root = new BinaryTreeNode(value);
            return;
        }
        BinaryTreeNode node = new BinaryTreeNode(value);
        BinaryTreeNode curr = root;
        while (true) {
            BinaryTreeNode parent = curr;
            if (value > curr.getValue()) {
                curr = curr.getRightChild();
                if (curr == null) {
                    parent.setRightChild(node);
                    return;
                }
            } else if (value < curr.getValue()) {
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
    public boolean deleteNode(Integer value) {
        if (value == null) {
            System.out.println("No operation for null value.");
            return false;
        }
        if (root == null) {
            System.err.println("Tree null. Deletion failed.");
            return false;
        }
        // search out the node
        BinaryTreeNode curr = root;
        BinaryTreeNode parent = root;
        boolean isInLeft = true;
        while (value != curr.getValue()) {
            isInLeft = value < curr.getValue();
            parent = curr;
            curr = value > curr.getValue() ? curr.getRightChild() : curr.getLeftChild();
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
                    "Deleting node " + value + " with " + curr.getLeftChild() != null ? "left" : "right" + " child.");
            if (isInLeft) {
                parent.setLeftChild(curr.getLeftChild());
            } else {
                parent.setRightChild(curr.getRightChild());
            }
            return true;
        }
        System.out.println("Deleting node " + value + " with children.");
        BinaryTreeNode predecessor = getPredecessor(curr);
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

    @Override
    BinaryTreeNode search(Integer value) {
        if (value == null) {
            System.err.println("Value " + value + " not exist.");
            return null;
        }
        if (root == null) {
            System.err.println("Empty root. Tree has not been initialized.");
            return null;
        }
        BinaryTreeNode curr = root;
        while (value != curr.getValue()) {
            curr = value > curr.getValue() ? curr.getRightChild() : curr.getLeftChild();
            if (curr == null) {
                return null;
            }
        }
        return curr;
    }

    private BinaryTreeNode getPredecessor(BinaryTreeNode node) {
        BinaryTreeNode predParent = node;
        BinaryTreeNode predecessor = node;
        BinaryTreeNode curr = node.getLeftChild();
        while (curr != null) {
            predParent = predecessor;
            predecessor = curr;
            curr = curr.getRightChild();
        }
        if (predecessor != node.getLeftChild()) {
            predParent.setRightChild(predecessor.getLeftChild());
            predecessor.setRightChild(node.getRightChild());
        }
        return predecessor;
    }

    public static void main(String[] args) {
        int[] a = { 7, 4, 3, 11, 2, 6, 10, 25, 9, 1 };
        BinarySearchTree tree = new BinarySearchTree();
        for (int i : a) {
            tree.insertNode(i);
        }
        System.out.println("\nPre order:");
        tree.preOrder(tree.getRoot());
        System.out.println("\nIn order:");
        tree.inOrder(tree.getRoot());
        System.out.println("\nPos order:");
        tree.posOrder(tree.getRoot());
        System.out.println("\nLevel order:");
        tree.levelOrder(tree.getRoot());

        tree.deleteNode(4);
        tree.deleteNode(2);
        System.out.println("\nLevel order after delete:");
        tree.levelOrder(tree.getRoot());
    }

}