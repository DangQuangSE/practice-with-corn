/**
 * Bài 10.7 — Longest Common Subsequence
 * Difficulty: Medium
 */
public class LongestCommonSubsequence {
    public static int solve(String first, String second) {
        // TODO: nếu ký tự cuối giống nhau thì lấy chéo + 1; ngược lại lấy max hai hướng.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("lcs", solve("abcde", "ace"), 3);
        Check.summary();
    }
}
