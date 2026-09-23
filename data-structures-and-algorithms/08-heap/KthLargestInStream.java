import java.util.PriorityQueue;

/**
 * Bài 8.3 — Kth Largest Element in a Stream
 * Difficulty: Medium
 */
public class KthLargestInStream {
    static class KthLargest {
        KthLargest(int k, int[] initial) { /* TODO */ }
        int add(int value) { return 0; /* TODO */ }
    }

    public static int[] solve(int k, int[] initial, int[] additions) {
        // TODO: min-heap kích thước k.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("stream results",
                solve(3, new int[] { 4, 5, 8, 2 }, new int[] { 3, 5, 10, 9, 4 }),
                new int[] { 4, 5, 5, 8, 8 });
        Check.summary();
    }
}
