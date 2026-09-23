/**
 * Bài 10.2 — House Robber
 * Difficulty: Easy
 */
public class HouseRobber {
    public static int solve(int[] nums) {
        // TODO: chọn bỏ qua nhà hiện tại hoặc lấy nums[i] + dp[i-2].
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("rob alternate houses", solve(new int[] { 1, 2, 3, 1 }), 4);
        Check.expect("two houses", solve(new int[] { 2, 7 }), 7);
        Check.summary();
    }
}
