import java.util.Arrays;

public class MergeSort {

    public static void sortTD(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    /**
     * Top-down merge sort
     * 
     * @param a
     * @param start
     * @param end
     */
    private static void mergeSort(int[] a, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(a, start, mid);
            mergeSort(a, mid + 1, end);
            merge(a, start, mid, end);
        }
    }

    /**
     * Bottom-up merge sort
     * 
     * @param a
     */
    public static void sortBU(int[] a) {
        for (int i = 1; i < a.length; i *= 2) {
            for (int j = 0; j < a.length - i; j += 2 * i) {
                merge(a, j, j + i - 1, Math.min(j + 2 * i - 1, a.length - 1));
            }
        }
    }

    private static void merge(int[] a, int low, int middle, int high) {
        int[] tmp = Arrays.copyOf(a, a.length);
        int left = low;
        int right = middle + 1;
        int curr = low;
        while (left <= middle && right <= high) {
            a[curr++] = tmp[left] <= tmp[right] ? tmp[left++] : tmp[right++];
        }
        // take care of the left-overs
        while (left <= middle) {
            a[curr++] = tmp[left++];
        }
        while (right <= high) {
            a[curr++] = tmp[right++];
        }
    }

    public static void main(String[] args) {
        int[] b = { 10, 75, 41, 5, 3, 8, 231, 660, 27, 61, 49, 38, 66, 90, 1, 0, 15 };
        sortTD(b);
        for (int i : b) {
            System.out.println(i);
        }
        int[] c = { 10, 75, 41, 5, 3, 8, 231, 660, 27, 61, 49, 38, 66, 90, 1, 0, 15 };
        sortBU(c);
        for (int i : c) {
            System.out.println(i);
        }
    }
}