import java.util.HashSet;
import java.util.Set;

/**
 * Bài 4.6 — Longest Consecutive Sequence
 * Difficulty: Medium
 */
public class LongestConsecutiveSequence {
    public static int solve(int[] nums) {
        // TODO: chỉ bắt đầu đếm khi nums[i]-1 không tồn tại trong set.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("basic sequence",
                solve(new int[] { 100, 4, 200, 1, 3, 2 }), 4);
        Check.summary();
    }
}
