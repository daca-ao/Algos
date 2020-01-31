public class BinaryTreeNode<T extends Comparable> {

    private T value;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private int height;

    public BinaryTreeNode(T value) {
        this.value = value;
        this.height = 1;
    }

    public BinaryTreeNode(BinaryTreeNode<T> node) {
        if (node == null) {
            throw new IllegalArgumentException("ERROR: param node cannot be null.");
        }
        this.value = node.getValue();
        this.leftChild = node.getLeftChild();
        this.rightChild = node.getRightChild();
        this.height = 1;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
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

}