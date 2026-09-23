import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.7 — Largest Rectangle in Histogram
 * Difficulty: Medium
 */
public class LargestRectangleInHistogram {
    public static int solve(int[] heights) {
        // TODO: stack tăng dần; khi pop, tính chiều rộng qua hai biên.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("basic histogram",
                solve(new int[] { 2, 1, 5, 6, 2, 3 }), 10);
        Check.summary();
    }
}
