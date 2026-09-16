import java.util.HashMap;
import java.util.Map;

/**
 * Bài 1.16 — Subarray Sum Equals K
 *
 * Đếm số dãy con liên tiếp có tổng đúng bằng k. Mảng có thể chứa số âm và số 0.
 * Input: nums=[1,1,1], k=2 -> Output: 2
 *
 * Mục tiêu: O(n) thời gian, O(n) bộ nhớ phụ bằng prefix sum + HashMap.
 */
public class SubarraySumEqualsK {

    public static int solve(int[] nums, int k) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("SubarraySumEqualsK");

        Check.expect("basic example", solve(new int[] { 1, 1, 1 }, 2), 2);

        Check.expect("single and combined subarrays",
                solve(new int[] { 1, 2, 3 }, 3), 2);

        Check.expect("negative values", solve(new int[] { -1, -1, 1 }, 0), 1);

        Check.expect("all zero subarrays with k zero",
                solve(new int[] { 0, 0, 0 }, 0), 6);

        Check.expect("no matching subarray",
                solve(new int[] { 1, 2, 3 }, 100), 0);

        Check.expect("negative target",
                solve(new int[] { 1, -1, 1, -1 }, -1), 3);

        Check.summary();
    }
}
