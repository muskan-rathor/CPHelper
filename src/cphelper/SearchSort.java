package cphelper;

import java.util.Arrays;

public final class SearchSort {
    private SearchSort() {}

    // wrapper for Arrays.sort on primitives or objects
    public static void sort(int[] arr) { Arrays.sort(arr); }

    // binary search lower_bound: first index >= key
    public static int lowerBound(int[] a, int key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] < key) l = m + 1;
            else r = m;
        }
        return l;
    }

    // upper_bound: first index > key
    public static int upperBound(int[] a, int key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] <= key) l = m + 1;
            else r = m;
        }
        return l;
    }
}
