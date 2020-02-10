/**
 * Output test class for trees.
 */
public class TreeTest {

    public static void main(String[] args) {
        int[] a = { 7, 4, 2, 11, 3, 6, 10, 21, 9, 1, 19, 17, 20, 22 };
        System.out.println("\n********** Binary Search Tree Test:");
        BinarySearchTree<Integer> bstTree = new BinarySearchTree<>();
        for (int i : a) {
            bstTree.insertNode(i);
        }
        System.out.println("\nPre order:");
        bstTree.preOrder(bstTree.getRoot());
        System.out.println("\nIn order:");
        bstTree.inOrder(bstTree.getRoot());
        System.out.println("\nPos order:");
        bstTree.posOrder(bstTree.getRoot());
        System.out.println("\nLevel order:");
        bstTree.levelOrder(bstTree.getRoot());

        System.out.println();
        bstTree.deleteNode(10);
        bstTree.deleteNode(1);
        bstTree.deleteNode(21);
        System.out.println("\nLevel order after delete:");
        bstTree.levelOrder(bstTree.getRoot());

        System.out.println("\n********** AVL Tree Test:");
        AVLTree<Integer> avlTree = new AVLTree<>();
        for (int i : a) {
            avlTree.insertNode(i);
        }
        System.out.println("\nPre order:");
        avlTree.preOrder(avlTree.getRoot());
        System.out.println("\nIn order:");
        avlTree.inOrder(avlTree.getRoot());
        System.out.println("\nPos order:");
        avlTree.posOrder(avlTree.getRoot());
        System.out.println("\nLevel order:");
        avlTree.levelOrder(avlTree.getRoot());

        System.out.println();
        avlTree.deleteNode(10);
        avlTree.deleteNode(1);
        avlTree.deleteNode(21);
        System.out.println("\nLevel order after delete:");
        avlTree.levelOrder(avlTree.getRoot());

        int[] b = { 12, 1, 9, 2, 0, 11, 7, 19, 4, 15, 18, 5, 14, 13, 10, 16, 6, 3, 8, 17 };
        System.out.println("\n********** Red-Black Tree Test:");
        RBTree<Integer> rbTree = new RBTree<>();
        for (int i : b) {
            rbTree.insertNode(i);
        }
        System.out.println("\nPre order:");
        rbTree.preOrder(rbTree.getRoot());
        System.out.println("\nIn order:");
        rbTree.inOrder(rbTree.getRoot());
        System.out.println("\nPos order:");
        rbTree.posOrder(rbTree.getRoot());
        System.out.println("\nLevel order:");
        rbTree.levelOrder(rbTree.getRoot());

        System.out.println();
        // rbTree.deleteNode(12);
        // rbTree.deleteNode(1);
        // rbTree.deleteNode(9);
        System.out.println("\nLevel order after delete:");
        rbTree.levelOrder(rbTree.getRoot());
    }
}