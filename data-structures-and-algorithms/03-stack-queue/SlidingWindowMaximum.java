import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.5 — Sliding Window Maximum
 * Difficulty: Medium
 */
public class SlidingWindowMaximum {
    public static int[] solve(int[] nums, int k) {
        // TODO: deque lưu chỉ số, giữ giá trị giảm dần.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("basic example",
                solve(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3),
                new int[] { 3, 3, 5, 5, 6, 7 });
        Check.summary();
    }
}
