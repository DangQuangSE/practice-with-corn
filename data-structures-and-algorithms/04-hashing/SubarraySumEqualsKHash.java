import java.util.HashMap;
import java.util.Map;

/**
 * Bài 4.5 — Subarray Sum Equals K
 * Difficulty: Medium
 */
public class SubarraySumEqualsKHash {
    public static int solve(int[] nums, int k) {
        // TODO: prefix hiện tại - k là prefix cần tìm trong HashMap.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("basic example", solve(new int[] { 1, 1, 1 }, 2), 2);
        Check.expect("zeros", solve(new int[] { 0, 0, 0 }, 0), 6);
        Check.summary();
    }
}
