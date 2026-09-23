/**
 * Bài 10.1 — Climbing Stairs
 * Difficulty: Easy
 */
public class ClimbingStairs {
    public static int solve(int n) {
        // TODO: dp[i] = dp[i-1] + dp[i-2], tối ưu còn hai biến.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("three stairs", solve(3), 3);
        Check.expect("one stair", solve(1), 1);
        Check.summary();
    }
}
