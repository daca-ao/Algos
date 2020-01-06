public class BubbleSort {

    public static void sort(int[] a) {
        final int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] > a[j + 1]) { // ascending order
                    final int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] b = { 3, 6, 12, 9, 0, 4, 1, 21 };
        BubbleSort.sort(b);
        for (final int i : b) {
            System.out.println(i);
        }
    }
}