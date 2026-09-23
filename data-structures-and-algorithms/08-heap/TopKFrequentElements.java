import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Bài 8.4 — Top K Frequent Elements
 * Difficulty: Medium
 */
public class TopKFrequentElements {
    public static int[] solve(int[] nums, int k) {
        // TODO: đếm tần suất rồi dùng min-heap kích thước k.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("top two",
                solve(new int[] { 1, 1, 1, 2, 2, 3 }, 2),
                new int[] { 1, 2 });
        Check.summary();
    }
}
