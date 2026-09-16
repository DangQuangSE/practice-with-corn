/**
 * Bài 1.10 — Minimum Size Subarray Sum
 *
 * Tìm độ dài nhỏ nhất của dãy con liên tiếp có tổng >= target.
 * nums chỉ chứa số dương. Nếu không có dãy phù hợp, trả về 0.
 * Input: nums=[2,3,1,2,4,3], target=7 -> Output: 2
 *
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ bằng sliding window biến đổi.
 */
public class MinimumSizeSubarraySum {

    public static int solve(int[] nums, int target) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("MinimumSizeSubarraySum");

        Check.expect("basic example",
                solve(new int[] { 2, 3, 1, 2, 4, 3 }, 7), 2);

        Check.expect("single element reaches target",
                solve(new int[] { 5 }, 5), 1);

        Check.expect("shrink window to one element",
                solve(new int[] { 1, 4, 4 }, 4), 1);

        Check.expect("no valid subarray",
                solve(new int[] { 1, 1, 1, 1 }, 11), 0);

        Check.expect("must combine several elements",
                solve(new int[] { 1, 2, 3, 4, 5 }, 11), 3);

        Check.expect("target larger than total sum",
                solve(new int[] { 1, 2 }, 10), 0);

        Check.expect("exact total sum",
                solve(new int[] { 2, 2, 2 }, 6), 3);

        Check.summary();
    }
}
