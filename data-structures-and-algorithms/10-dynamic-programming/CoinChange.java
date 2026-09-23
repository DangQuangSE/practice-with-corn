import java.util.Arrays;

/**
 * Bài 10.4 — Coin Change
 * Difficulty: Medium
 */
public class CoinChange {
    public static int solve(int[] coins, int amount) {
        // TODO: dp[amount] là số xu ít nhất; dùng giá trị vô hạn cho trạng thái chưa đạt.
        return -1;
    }

    public static void main(String[] args) {
        Check.expect("minimum coins", solve(new int[] { 1, 2, 5 }, 11), 3);
        Check.expect("impossible amount", solve(new int[] { 2 }, 3), -1);
        Check.summary();
    }
}
