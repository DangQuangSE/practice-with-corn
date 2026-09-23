/**
 * Bài 10.5 — Longest Increasing Subsequence
 * Difficulty: Medium
 */
public class LongestIncreasingSubsequence {
    public static int solve(int[] nums) {
        // TODO: bắt đầu O(n^2); sau đó thử tối ưu bằng tails + binary search.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("lis length",
                solve(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }), 4);
        Check.summary();
    }
}
