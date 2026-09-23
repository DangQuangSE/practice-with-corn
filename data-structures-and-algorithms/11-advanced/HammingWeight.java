/**
 * Bài 11.1 — Hamming Weight
 * Difficulty: Easy
 */
public class HammingWeight {
    public static int solve(int n) {
        // TODO: thử n & (n - 1) để xóa bit 1 thấp nhất mỗi vòng.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("1011 has three bits", solve(11), 3);
        Check.summary();
    }
}
