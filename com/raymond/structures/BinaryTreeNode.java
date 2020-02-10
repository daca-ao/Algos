/**
 * Reference: HashMap.TreeNode
 * 
 * @param <T> Genericity for node value
 */
public class BinaryTreeNode<T extends Comparable> {

    private T value;
    private boolean isRed = true; // default: mark it as red
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private int height;

    public BinaryTreeNode(T value) {
        this.value = value;
        this.height = 1;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public void printValue() {
        System.out.println("Binary tree node value: " + getValue());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isRed() {
        return isRed;
    }

    /**
     * Set color of Red-Black Tree node
     * 
     * @param isRed true for RED, false for BLACK
     */
    public void setColor(boolean isRed) {
        this.isRed = isRed;
    }

}