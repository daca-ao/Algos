public class BubbleSort {

    public static void sort(int[] a) {
        final int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) { // ascending order
                    Swapper.swap(a, j, j + 1);
                }
            }
        }
    }

    public static void sortE(int[] a) {
        final int n = a.length;
        boolean needSwap = true;
        for (int i = 0; i < n - 1 && needSwap; i++) {
            needSwap = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    Swapper.swap(a, j, j + 1);
                    needSwap = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] b = { 3, 6, 12, 9, 0, 4, 1, 21 };
        sort(b);
        for (final int i : b) {
            System.out.println(i);
        }
        int[] c = { 3, 6, 12, 9, 0, 4, 1, 21 };
        sortE(c);
        for (final int i : c) {
            System.out.println(i);
        }
    }
}