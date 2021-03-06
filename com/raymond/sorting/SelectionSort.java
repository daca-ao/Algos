public class SelectionSort {

    public static void sort(int[] a) {
        final int length = a.length;
        int minIndex;
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Swapper.swap(a, minIndex, i);
            }
        }
    }

    public static void sortEarlyTerm(int[] a) {
        final int length = a.length;
        boolean sorted = false;
        for (int size = length; !sorted && size > 1; size--) {
            int maxIndex = 0;
            sorted = true;
            for (int i = 1; i < size; i++) {
                if (a[i] > a[maxIndex]) {
                    maxIndex = i;
                } else {
                    sorted = false;
                }
            }
            if (maxIndex != size - 1) {
                Swapper.swap(a, maxIndex, size - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] b = { 7, 631, 12, 43, 6, 58, 102, 9, 0, 84 };
        sort(b);
        for (int i : b) {
            System.out.println(i);
        }
        int[] c = { 7, 523, 12, 43, 6, 58, 702, 9, 0, 84, 117 };
        sortEarlyTerm(c);
        for (int i : c) {
            System.out.println(i);
        }
    }
}