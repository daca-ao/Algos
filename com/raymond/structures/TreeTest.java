/**
 * Output test class for trees.
 */
public class TreeTest {

    public static void main(String[] args) {
        int[] a = { 7, 4, 2, 11, 3, 6, 10, 21, 9, 1, 19, 17, 20, 22 };
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
    }
}