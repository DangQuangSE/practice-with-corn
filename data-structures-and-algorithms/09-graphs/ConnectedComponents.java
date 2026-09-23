import java.util.ArrayList;
import java.util.List;

/**
 * Bài 9.1 — Number of Connected Components
 * Difficulty: Easy
 */
public class ConnectedComponents {
    public static int solve(int n, int[][] edges) {
        // TODO: dựng adjacency list, DFS/BFS từ mỗi node chưa thăm.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("two components",
                solve(5, new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 } }), 2);
        Check.summary();
    }
}
