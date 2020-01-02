public class SelectionSort {

    public static void sort(int[] a) {
        final int length = a.length;
        int minIndex;
        for (int i = 0; i < length; i++) {
            minIndex = i;
            for (int j = i; j < length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                final int temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] b = { 7, 631, 12, 43, 6, 58, 102, 9, 0, 84 };
        SelectionSort.sort(b);
        for (int i : b) {
            System.out.println(i);
        }
    }
}