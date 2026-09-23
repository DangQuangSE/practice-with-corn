import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Bài 8.6 — Find Median from Data Stream
 * Difficulty: Medium
 */
public class FindMedianFromDataStream {
    static class MedianFinder {
        void addNum(int value) { /* TODO */ }
        double findMedian() { return 0.0; /* TODO */ }
    }

    public static double[] solve(int[] values) {
        // TODO: max-heap nửa nhỏ, min-heap nửa lớn, luôn cân bằng kích thước.
        return new double[0];
    }

    public static void main(String[] args) {
        Check.expect("running medians",
                solve(new int[] { 1, 2, 3 }),
                new double[] { 1.0, 1.5, 2.0 });
        Check.summary();
    }
}
