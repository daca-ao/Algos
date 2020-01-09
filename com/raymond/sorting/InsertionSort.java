public class InsertionSort {

    public static void sort(int[] a) {
        final int length = a.length;

        for (int i = 1; i < length; i++) {
            int toBeInserted = a[i]; // get the one to be inserted into the ordered list

            for (int j = i; j >= 0; j--) {
                if (j > 0 && a[j - 1] > toBeInserted) { // that one smaller than the one in ordered list
                    a[j] = a[j - 1]; // swift ahead
                } else {
                    a[j] = toBeInserted; // set that one into the ordered list
                    break;
                }
            }
        }
    }

    public static void sortThenSwap(int[] a) {
        final int length = a.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    // below: behave like SWAP(Bubble Sort, Quicksort)
                    Swapper.swap(a, j, j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] b = { 4, 5, 1, 0, 14, 22, 16, 31, 67, 58, 49 };
        InsertionSort.sort(b);
        for (int i : b) {
            System.out.println(i);
        }
        int[] c = { 7, 2, 35, 110, 87, 5, 202, 1, 23, 47, 89, 60 };
        InsertionSort.sortThenSwap(c);
        for (int i : c) {
            System.out.println(i);
        }
    }
}