import java.util.HashMap;
import java.util.Map;

/**
 * Bài 4.8 — Four Sum II
 * Difficulty: Medium
 */
public class FourSumII {
    public static int solve(int[] a, int[] b, int[] c, int[] d) {
        // TODO: lưu tần suất tổng của từng cặp a+b, rồi tra -(c+d).
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("basic tuples",
                solve(new int[] { 1, 2 }, new int[] { -2, -1 },
                        new int[] { -1, 2 }, new int[] { 0, 2 }),
                2);
        Check.summary();
    }
}
