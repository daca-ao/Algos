public class RBTree<T extends Comparable> extends AbstractBinarySearchTree<T> implements IRotateManager<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    @Override
    public void insertNode(T value) {
        if (value == null) {
            System.err.println("Please do not insert a null value.");
            return;
        }
        if (root == null) {
            root = new BinaryTreeNode<>(value);
            root.setColor(BLACK);
            return;
        }
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        BinaryTreeNode<T> parent = root;
        BinaryTreeNode<T> curr = root;
        while (curr != null) {
            int cmpResult = value.compareTo(curr.getValue());
            parent = curr;
            if (cmpResult > 0) {
                curr = curr.getRightChild();
            } else if (cmpResult < 0) {
                curr = curr.getLeftChild();
            } else {
                System.err.println("Node " + value + " already there.");
                return;
            }
        }
        node.setParent(parent);

        int cmp = value.compareTo(parent.getValue());
        if (cmp < 0) {
            parent.setLeftChild(node);
        } else {
            parent.setRightChild(node);
        }

        // Check nodes' color then rotate if necessary
        insertFixup(node);
    }

    @Override
    public boolean deleteNode(T value) {
        // search out the node to be deleted
        BinaryTreeNode<T> node = search(value);
        if (node == null) {
            System.err.println("Element not found.");
            return false;
        }

        System.out.println("Deleting node " + value + ".");

        BinaryTreeNode<T> replacement = node.getLeftChild() == null || node.getRightChild() == null ? node
                : getPredecessor(node);
        BinaryTreeNode<T> subTreeToBeAdjusted = replacement.getLeftChild() != null ? replacement.getLeftChild()
                : replacement.getRightChild();

        // set to remove the predecessor
        BinaryTreeNode<T> parent = replacement.getParent();
        if (subTreeToBeAdjusted != null) {
            subTreeToBeAdjusted.setParent(parent);
        }
        if (parent == null) {
            root = subTreeToBeAdjusted;
        } else if (replacement == parent.getLeftChild()) {
            parent.setLeftChild(subTreeToBeAdjusted);
        } else {
            parent.setRightChild(subTreeToBeAdjusted);
        }

        // to reduce the effort:
        // simply replace the value of the node to be deleted by that of replacement
        // reduce pointer switching
        if (replacement != node) {
            node.setValue(replacement.getValue());
        }

        // subtree's parent (the deleted node) is BLACK: need adjustment
        // no need for adjusting tree that a RED node is removed
        if (!replacement.isRed()) {
            deleteFixup(subTreeToBeAdjusted, parent);
        }
        return true;
    }

    @Override
    public BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> node) {
        if (node == null || node.getRightChild() == null) {
            throw new IllegalArgumentException("Node does not have right child.");
        }
        BinaryTreeNode<T> rotate = node.getRightChild();
        node.setRightChild(rotate.getLeftChild());
        if (rotate.getLeftChild() != null) {
            rotate.getLeftChild().setParent(node);
        }
        rotate.setParent(node.getParent());

        // add rotate to parent
        if (node.getParent() == null) {
            root = rotate;
        } else if (node == node.getParent().getLeftChild()) {
            node.getParent().setLeftChild(rotate);
        } else {
            node.getParent().setRightChild(rotate);
        }
        rotate.setLeftChild(node);
        node.setParent(rotate);
        return rotate;
    }

    @Override
    public BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> node) {
        if (node == null || node.getLeftChild() == null) {
            throw new IllegalArgumentException("Node does not have left child.");
        }
        BinaryTreeNode<T> rotate = node.getLeftChild();
        node.setLeftChild(rotate.getRightChild());
        if (rotate.getRightChild() != null) {
            rotate.getRightChild().setParent(node);
        }
        rotate.setParent(node.getParent());

        // add rotate to parent
        if (node.getParent() == null) {
            root = rotate;
        } else if (node == node.getParent().getLeftChild()) {
            node.getParent().setLeftChild(rotate);
        } else {
            node.getParent().setRightChild(rotate);
        }
        rotate.setRightChild(node);
        node.setParent(rotate);
        return rotate;
    }

    private void insertFixup(BinaryTreeNode<T> node) {
        // We only need to fix up when parent is RED.
        BinaryTreeNode<T> papa;
        while ((papa = node.getParent()) != null && papa.isRed()) {
            BinaryTreeNode<T> grandPa = node.getParent().getParent();
            // If parent is RED: grandparent must be there.
            // Skip the isNull judge

            if (papa == grandPa.getLeftChild()) {
                BinaryTreeNode<T> uncle = grandPa.getRightChild();
                if (uncle != null && uncle.isRed()) {
                    papa.setColor(BLACK);
                    uncle.setColor(BLACK);
                    grandPa.setColor(RED);
                    node = grandPa; // prepare for upper fixup
                    continue;
                }
                // else: uncle is BLACK(null or black node)
                if (node == papa.getRightChild()) { // if new node is as right child: need rotate first
                    papa = leftRotate(papa);
                    node = papa.getLeftChild();
                }
                papa.setColor(BLACK);
                grandPa.setColor(RED);
                rightRotate(grandPa);
            } else { // mirror operation
                BinaryTreeNode<T> uncle = grandPa.getLeftChild();
                if (uncle != null && uncle.isRed()) {
                    papa.setColor(BLACK);
                    uncle.setColor(BLACK);
                    grandPa.setColor(RED);
                    node = grandPa;
                    continue;
                }
                if (node == papa.getLeftChild()) {
                    papa = rightRotate(papa);
                    node = papa.getRightChild();
                }
                papa.setColor(BLACK);
                grandPa.setColor(RED);
                leftRotate(grandPa);
            }
        }
        root.setColor(BLACK);
    }

    /**
     * 
     * @param node
     * @param parent for erasing leaf node, in this case, node == null and it does
     *               not have brother
     */
    private void deleteFixup(BinaryTreeNode<T> node, BinaryTreeNode<T> parent) {
        // if node == root: BLACK counts for all links to leaves are reduced by 1 ->
        // It's OK.
        // but if node != root:
        while (root != node && (node == null || !node.isRed())) {
            if (parent.getLeftChild() == node) {
                BinaryTreeNode<T> brother = parent.getRightChild();
                if (brother == null) {
                    // brother cannot be null
                    throw new IllegalArgumentException("Brother node must exist.");
                }
                if (brother.isRed()) {
                    brother.setColor(BLACK);
                    parent.setColor(RED);
                    leftRotate(parent);
                    brother = parent.getRightChild(); // new brother that is black
                }

                // if brother is BLACK:
                if ((brother.getLeftChild() == null || !brother.getLeftChild().isRed())
                        && (brother.getRightChild() == null || !brother.getRightChild().isRed())) {
                    // all nephew nodes are BLACK
                    brother.setColor(RED);
                    node = parent; // prepare for upper fixup
                    parent = node.getParent();

                } else {
                    if (brother.getRightChild() == null || !brother.getRightChild().isRed()) {
                        // left nephew is RED, right nephew is BLACK
                        brother.setColor(RED);
                        brother.getLeftChild().setColor(BLACK);
                        brother = rightRotate(brother);
                    }
                    // no matter what color left nephew is, right nephew is RED
                    brother.setColor(parent.isRed());
                    brother.getRightChild().setColor(BLACK);
                    parent.setColor(BLACK);
                    leftRotate(parent);

                    // terminate the loop
                    node = root;
                    break;
                }

            } else { // mirror operation
                BinaryTreeNode<T> brother = parent.getLeftChild();
                if (brother == null) {
                    // brother cannot be null
                    throw new IllegalArgumentException("Brother node must exist.");
                }
                if (brother.isRed()) {
                    brother.setColor(BLACK);
                    parent.setColor(RED);
                    rightRotate(parent);
                    brother = parent.getLeftChild();
                }

                if ((brother.getLeftChild() == null || !brother.getLeftChild().isRed())
                        && (brother.getRightChild() == null || !brother.getRightChild().isRed())) {
                    brother.setColor(RED);
                    node = parent;
                    parent = node.getParent();

                } else {
                    if (brother.getLeftChild() == null || !brother.getLeftChild().isRed()) {
                        brother.setColor(RED);
                        brother.getRightChild().setColor(BLACK);
                        brother = leftRotate(brother);
                    }
                    brother.setColor(parent.isRed());
                    brother.getLeftChild().setColor(BLACK);
                    parent.setColor(BLACK);
                    rightRotate(parent);

                    node = root;
                    break;
                }
            }
        }

        if (node != null) {
            node.setColor(BLACK);
        }
    }

}