public class Swapper {

    public static void swap(int[] a, int x, int y) {
        final int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}