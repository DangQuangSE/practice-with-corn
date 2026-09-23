import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Bài 9.6 — Rotting Oranges
 * Difficulty: Medium
 */
public class RottingOranges {
    public static int solve(int[][] grid) {
        // TODO: BFS đa nguồn theo từng level/phút, đếm cam tươi còn lại.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("all oranges rot",
                solve(new int[][] { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } }), 4);
        Check.expect("impossible",
                solve(new int[][] { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } }), -1);
        Check.summary();
    }
}
