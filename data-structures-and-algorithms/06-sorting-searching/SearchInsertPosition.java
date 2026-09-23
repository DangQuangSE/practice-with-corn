/**
 * Bài 6.5 — Tìm vị trí chèn
 * Difficulty: Medium
 */
public class SearchInsertPosition {
    public static int solve(int[] nums, int target) {
        // TODO: binary search lower bound, vị trí đầu tiên nums[i] >= target.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("insert middle", solve(new int[] { 1, 3, 5, 6 }, 2), 1);
        Check.expect("insert end", solve(new int[] { 1, 3, 5, 6 }, 7), 4);
        Check.summary();
    }
}
