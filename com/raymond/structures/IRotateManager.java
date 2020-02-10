/**
 * Managing the rotate actions.
 */
public interface IRotateManager<T extends Comparable> {

    /**
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    BinaryTreeNode<T> rightRotate(BinaryTreeNode<T> node);

    /**
     * 
     * @param node the node to be balanced
     * @return new root of the sub-tree
     */
    BinaryTreeNode<T> leftRotate(BinaryTreeNode<T> node);
}