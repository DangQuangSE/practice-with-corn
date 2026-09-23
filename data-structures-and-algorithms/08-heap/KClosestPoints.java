import java.util.PriorityQueue;

/**
 * Bài 8.8 — K Closest Points to Origin
 * Difficulty: Medium
 */
public class KClosestPoints {
    public static int[][] solve(int[][] points, int k) {
        // TODO: max-heap kích thước k theo khoảng cách bình phương.
        return new int[0][];
    }

    public static void main(String[] args) {
        Check.expect("closest point",
                solve(new int[][] { { 1, 3 }, { -2, 2 } }, 1),
                new int[][] { { -2, 2 } });
        Check.summary();
    }
}
