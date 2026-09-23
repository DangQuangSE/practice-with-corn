import java.util.PriorityQueue;

/**
 * Bài 8.5 — Gộp K mảng đã sắp xếp bằng Priority Queue
 * Difficulty: Medium
 */
public class MergeKSortedArrays {
    public static int[] solve(int[][] arrays) {
        // TODO: heap chứa (giá trị, mảng, vị trí) của phần tử đầu mỗi nguồn.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("merge arrays",
                solve(new int[][] { { 1, 4 }, { 1, 3 }, { 2, 6 } }),
                new int[] { 1, 1, 2, 3, 4, 6 });
        Check.summary();
    }
}
