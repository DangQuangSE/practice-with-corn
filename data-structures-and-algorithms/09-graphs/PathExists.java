import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Bài 9.2 — Kiểm tra có đường đi giữa hai node
 * Difficulty: Easy
 */
public class PathExists {
    public static boolean solve(int n, int[][] edges, int source, int target) {
        // TODO: BFS hoặc DFS từ source.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("path exists",
                solve(3, new int[][] { { 0, 1 }, { 1, 2 } }, 0, 2), true);
        Check.expect("same source and target", solve(1, new int[0][0], 0, 0), true);
        Check.summary();
    }
}
