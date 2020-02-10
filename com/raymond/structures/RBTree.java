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
        // TODO
        return false;
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

}