/**
 * Bài 6.10 — Find First and Last Position of Element
 * Difficulty: Medium
 */
public class FindFirstAndLastPosition {
    public static int[] solve(int[] nums, int target) {
        // TODO: chạy hai binary search lower/upper bound.
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        Check.expect("range of target",
                solve(new int[] { 5, 7, 7, 8, 8, 10 }, 8),
                new int[] { 3, 4 });
        Check.summary();
    }
}
