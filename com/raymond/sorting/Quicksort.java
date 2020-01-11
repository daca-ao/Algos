public class Quicksort {

    public static void sort(int[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static void quicksort(int[] a, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int left = begin;
        int right = end;
        int base = a[begin];
        while (left != right) {
            // search element smaller than base from right to left
            while (a[right] >= base && left < right) {
                right--;
            }
            // then search element bigger than base from left to right
            while (a[left] <= base && left < right) {
                left++;
            }
            if (left < right) {
                Swapper.swap(a, left, right);
            }
        }

        // put base into middle
        a[begin] = a[left];
        a[left] = base;

        quicksort(a, begin, left - 1); // sort left part
        quicksort(a, left + 1, end); // sort right part
    }

    public static void main(String[] args) {
        int[] b = { 99, 13, 41, 0, 6, 27, 84, 32, 1, 5, 76, 38, 50, 118 };
        sort(b);
        for (int i : b) {
            System.out.println(i);
        }
    }
}