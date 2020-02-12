public class AVLTree<T extends Comparable> extends AbstractBinarySearchTree<T> implements IRotateManager<T> {

    @Override
    public void insertNode(T value) {
        if (value == null) {
            System.err.println("Please do not insert a null value.");
            return;
        }
        root = insertNode(root, value);
    }

    @Override
    public boolean deleteNode(T value) {
        if (root == null) {
            System.err.println("Tree null. Deletion failed.");
            return false;
        }
        if (search(value) != null) {
            root = deleteNode(root, value);
            return true;
        }
        return false;
    }

    @Override
    public BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> node) {
        if (node == null || node.getLeftChild() == null) {
            throw new IllegalArgumentException("Node does not have left child.");
        }
        BinaryTreeNode<T> rotate = node.getLeftChild();
        node.setLeftChild(rotate.getRightChild());
        rotate.setRightChild(node);
        return rotate;
    }

    @Override
    public BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> node) {
        if (node == null || node.getRightChild() == null) {
            throw new IllegalArgumentException("Node does not have right child.");
        }
        BinaryTreeNode<T> rotate = node.getRightChild();
        node.setRightChild(rotate.getLeftChild());
        rotate.setLeftChild(node);
        return rotate;
    }

    private BinaryTreeNode<T> insertNode(BinaryTreeNode<T> node, T value) {
        if (node == null) { // reach to the end: insert the node
            node = new BinaryTreeNode<>(value);
        } else {
            int cmpResult = value.compareTo(node.getValue());
            if (cmpResult > 0) {
                // new node goes to right child
                node.setRightChild(insertNode(node.getRightChild(), value));
                if (getHeight(node.getRightChild()) - getHeight(node.getLeftChild()) == 2) {
                    if (value.compareTo(node.getRightChild().getValue()) > 0) { // lies in right's right(RR)
                        node = singleRotateRight(node);
                    } else { // lies in right's left(RL)
                        node = doubleRotateRight(node);
                    }
                }
            } else if (cmpResult < 0) {
                // new node goes to left child
                node.setLeftChild(insertNode(node.getLeftChild(), value));
                if (getHeight(node.getLeftChild()) - getHeight(node.getRightChild()) == 2) {
                    if (value.compareTo(node.getLeftChild().getValue()) < 0) { // lies in left's left(LL)
                        node = singleRotateLeft(node);
                    } else { // lies in left's right(LR)
                        node = doubleRotateLeft(node);
                    }
                }
            } else {
                System.err.println("Node " + value + " already there.");
            }
        }
        node.setHeight(1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())));
        return node;
    }

    private BinaryTreeNode<T> deleteNode(BinaryTreeNode<T> node, T value) {
        if (value == null) {
            System.err.println("Value " + value + " not exist.");
            return null;
        }
        if (node == null) {
            System.err.println("Node not exist.");
            return null;
        }
        int cmpResult = value.compareTo(node.getValue());
        if (cmpResult < 0) { // in left child
            node.setLeftChild(deleteNode(node.getLeftChild(), value));
            if (getHeight(node.getRightChild()) - getHeight(node.getLeftChild()) == 2) {
                if (getHeight(node.getRightChild().getRightChild()) > getHeight(node.getRightChild().getLeftChild())) {
                    node = singleRotateRight(node);
                } else {
                    node = doubleRotateRight(node);
                }
            }
        } else if (cmpResult > 0) { // in right child
            node.setRightChild(deleteNode(node.getRightChild(), value));
            if (getHeight(node.getLeftChild()) - getHeight(node.getRightChild()) == 2) {
                if (getHeight(node.getLeftChild().getLeftChild()) > getHeight(node.getLeftChild().getRightChild())) {
                    node = singleRotateLeft(node);
                } else {
                    node = doubleRotateLeft(node);
                }
            }
        } else {
            // if the node to be deleted has children
            if (node.getLeftChild() != null && node.getRightChild() != null) {
                System.out.println("Deleting node " + value + " with children.");
                if (getHeight(node.getLeftChild()) > getHeight(node.getRightChild())) {
                    // the node has left child taller than right one
                    // then: get the predecessor
                    BinaryTreeNode<T> predecessor = getPredecessor(node);
                    node.setValue(predecessor.getValue());
                    node.setLeftChild(deleteNode(node.getLeftChild(), predecessor.getValue()));
                } else {
                    // if not: get the successor
                    BinaryTreeNode<T> successor = getSuccessor(node);
                    node.setValue(successor.getValue());
                    node.setRightChild(deleteNode(node.getRightChild(), successor.getValue()));
                }
            } else {
                // the node has only one child, or none
                System.out.println("Deleting node " + value + ".");
                node = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
            }
        }
        return node;
    }

    /**
     * Single rotate LL: new node in left child of node's left child. <br/>
     * Need to rotate right-ward.
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    private BinaryTreeNode<T> singleRotateLeft(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> rotate = rightRotate(node);
        node.setHeight(1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())));
        rotate.setHeight(1 + Math.max(getHeight(rotate.getLeftChild()), getHeight(node)));
        return rotate;
    }

    /**
     * Single rotate RR: new node in right child of node's right child. <br/>
     * Need to rotate left-ward.
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    private BinaryTreeNode<T> singleRotateRight(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> rotate = leftRotate(node);
        node.setHeight(1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())));
        rotate.setHeight(1 + Math.max(getHeight(node), getHeight(rotate.getRightChild())));
        return rotate;
    }

    /**
     * Double rotate LR: new node in right child of node's left child. <br/>
     * Need to rotate right-ward on node's left child first, then rotate left-ward
     * on the node.
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    private BinaryTreeNode<T> doubleRotateLeft(BinaryTreeNode<T> node) {
        // RR-rotate first
        node.setLeftChild(singleRotateRight(node.getLeftChild()));
        // then LL-rotate
        return singleRotateLeft(node);
    }

    /**
     * Double rotate RL: new node in left child of node's right child. <br/>
     * Need to rotate left-ward on node's right child first, then rotate right-ward
     * on the node.
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    private BinaryTreeNode<T> doubleRotateRight(BinaryTreeNode<T> node) {
        // LL-rotate first
        node.setRightChild(singleRotateLeft(node.getRightChild()));
        // then RR-rotate
        return singleRotateRight(node);
    }

    private int getHeight(BinaryTreeNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }

}