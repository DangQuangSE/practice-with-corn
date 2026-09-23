import java.util.Arrays;

/**
 * Bài 6.9 — Merge Intervals
 * Difficulty: Medium
 */
public class MergeIntervals {
    public static int[][] solve(int[][] intervals) {
        // TODO: sort theo start rồi gộp interval chồng lấn.
        return new int[0][];
    }

    public static void main(String[] args) {
        Check.expect("overlapping intervals",
                solve(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } }),
                new int[][] { { 1, 6 }, { 8, 10 }, { 15, 18 } });
        Check.summary();
    }
}
