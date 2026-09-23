import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Bài 9.3 — Number of Islands
 * Difficulty: Easy
 */
public class NumberOfIslands {
    public static int solve(char[][] grid) {
        // TODO: gặp '1' thì DFS/BFS toàn bộ đảo và đánh dấu đã thăm.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("one island",
                solve(new char[][] {
                        { '1', '1', '0', '0', '0' },
                        { '1', '1', '0', '0', '0' },
                        { '0', '0', '1', '0', '0' },
                        { '0', '0', '0', '1', '1' }
                }), 3);
        Check.summary();
    }
}
