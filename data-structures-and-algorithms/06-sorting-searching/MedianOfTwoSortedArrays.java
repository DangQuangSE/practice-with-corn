/**
 * Bài 6.8 — Median của hai mảng đã sắp xếp
 * Difficulty: Medium
 */
public class MedianOfTwoSortedArrays {
    public static double solve(int[] first, int[] second) {
        // TODO: binary search partition trên mảng ngắn hơn.
        return 0.0;
    }

    public static void main(String[] args) {
        Check.expect("even total length",
                solve(new int[] { 1, 3 }, new int[] { 2, 4 }), 2.5);
        Check.summary();
    }
}
