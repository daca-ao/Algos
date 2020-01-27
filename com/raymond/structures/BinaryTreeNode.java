public class BinaryTreeNode {

    private Integer value;
    private BinaryTreeNode leftChild;
    private BinaryTreeNode rightChild;

    public BinaryTreeNode(Integer value) {
        this.value = value;
    }

    public BinaryTreeNode(BinaryTreeNode node) {
        if (node == null) {
            throw new IllegalArgumentException("ERROR: param node cannot be null.");
        }
        this.value = node.getValue();
        this.leftChild = node.getLeftChild();
        this.rightChild = node.getRightChild();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public BinaryTreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public void printValue() {
        System.out.println("Binary tree node value: " + getValue());
    }

}