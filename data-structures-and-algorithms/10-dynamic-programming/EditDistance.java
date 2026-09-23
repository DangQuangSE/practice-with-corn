/**
 * Bài 10.8 — Edit Distance
 * Difficulty: Medium
 */
public class EditDistance {
    public static int solve(String word1, String word2) {
        // TODO: dp[i][j] cho phép xóa, thêm hoặc sửa ký tự cuối.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("horse to ros", solve("horse", "ros"), 3);
        Check.expect("empty target", solve("abc", ""), 3);
        Check.summary();
    }
}
