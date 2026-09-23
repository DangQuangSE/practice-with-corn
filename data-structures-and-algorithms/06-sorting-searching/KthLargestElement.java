import java.util.PriorityQueue;

/**
 * Bài 6.7 — Kth Largest Element
 * Difficulty: Medium
 */
public class KthLargestElement {
    public static int solve(int[] nums, int k) {
        // TODO: thử min-heap kích thước k hoặc Quickselect.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("second largest", solve(new int[] { 3, 2, 1, 5, 6, 4 }, 2), 5);
        Check.summary();
    }
}
