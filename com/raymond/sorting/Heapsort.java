public class Heapsort {

    /**
     * Apply 1-dimen array for max heap: 
     * pos for left = (pos for father) * 2 + 1 
     * pos for right = (pos for father) * 2 + 2
     * 
     * aka. a[i] >= a[2i + 1], a[i] >= a[2i + 2]
     * 
     * @param a
     */
    public static void sort(int[] a) {
        final int length = a.length;

        // begin from the first non-leaf node
        for (int i = length / 2 - 1; i >= 0; i--) {
            buildHeap(a, i, length);
        }
        // output: replace the root of max heap with the tail value of list
        // then: build the heap again
        for (int i = length - 1; i > 0; i--) {
            Swapper.swap(a, 0, i);
            buildHeap(a, 0, i);
        }
    }

    /**
     * 
     * @param a the array represents the max heap
     * @param root the root node of the max heap
     * @param size nodes number of this max heap
     */
    private static void buildHeap(int[] a, int root, int size) {
        int pos = root;
        int originalRootVal = a[root]; // extract the value of root node first
        int maxIndex = pos * 2 + 1; // set maxIndex default to that of left child

        while (maxIndex < size) {
            if (maxIndex + 1 < size && a[maxIndex] < a[maxIndex + 1]) { // if right child larger than left
                maxIndex += 1; // set right child as the larger
            }
            if (a[maxIndex] > originalRootVal) {
                a[pos] = a[maxIndex]; // replace the root now with the maximum node
                pos = maxIndex; // set left/right child as the new root
                maxIndex = pos * 2 + 1; // set the new maxIndex default to left child of the child
            } else {
                break; // father already larger than child: break
            }
        }

        // Finally all larger value moved to root: set the original root to the position
        a[pos] = originalRootVal;
    }

    public static void main(String[] args) {
        int[] b = { 24, 4, 5, 30, 17, 8, 0, 51, 86 };
        Heapsort.sort(b);
        for (int i : b) {
            System.out.println(i);
        }
    }
}