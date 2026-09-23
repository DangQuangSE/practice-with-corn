import java.util.PriorityQueue;

/**
 * Bài 8.7 — Task Scheduler
 * Difficulty: Medium
 */
public class TaskScheduler {
    public static int solve(char[] tasks, int cooldown) {
        // TODO: max-heap tần suất + queue cooldown.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("two task types",
                solve(new char[] { 'A', 'A', 'A', 'B', 'B', 'B' }, 2), 8);
        Check.summary();
    }
}
