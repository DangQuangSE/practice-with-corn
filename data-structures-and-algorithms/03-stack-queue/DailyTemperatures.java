import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.6 — Daily Temperatures
 * Difficulty: Medium
 */
public class DailyTemperatures {
    public static int[] solve(int[] temperatures) {
        // TODO: monotonic stack lưu chỉ số các ngày chưa có ngày ấm hơn.
        return new int[temperatures.length];
    }

    public static void main(String[] args) {
        Check.expect("wait days",
                solve(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 }),
                new int[] { 1, 1, 4, 2, 1, 1, 0, 0 });
        Check.summary();
    }
}
