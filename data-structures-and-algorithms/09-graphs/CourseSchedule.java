import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Bài 9.5 — Course Schedule
 * Difficulty: Medium
 */
public class CourseSchedule {
    public static boolean solve(int courses, int[][] prerequisites) {
        // TODO: Kahn BFS hoặc DFS 3 trạng thái để phát hiện chu trình.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("acyclic prerequisites",
                solve(2, new int[][] { { 1, 0 } }), true);
        Check.expect("cycle",
                solve(2, new int[][] { { 1, 0 }, { 0, 1 } }), false);
        Check.summary();
    }
}
