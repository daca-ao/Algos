public class RadixSort {

    public static void sort(int[] a) {
        final int length = a.length;
        int[][] buckets = new int[10][length];
        int[] bucket = new int[10]; // store how many elements are there in the bucket
        for (int i = 0, factor = 1; i < maxBit(a); i++, factor *= 10) { // start from LSD
            for (int j = 0; j < length; j++) {
                int digit = (a[j] / factor) % 10;
                buckets[digit][bucket[digit]] = a[j];
                bucket[digit]++;
            }
            for (int m = 0, k = 0; m < 10; m++) {
                if (0 == bucket[m]) {
                    continue;
                }
                for (int n = 0; n < bucket[m]; n++) {
                    a[k] = buckets[m][n];
                    k++;
                }
                bucket[m] = 0;

            }
        }
    }

    private static int maxBit(int[] a) { // get the max bit from the array
        int maxLength = 0;
        for (int i : a) {
            int size = Integer.toString(i).length();
            maxLength = maxLength >= size ? maxLength : size;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] b = { 124, 5, 23, 68, 41, 55, 0, 90, 14, 18, 5, 2, 33, 17, 19, 50, 40, 37, 7, 214 };
        sort(b);
        for (int i : b) {
            System.out.println(i);
        }
    }
}