import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.4 — Min Stack
 * Difficulty: Medium
 */
public class MinStack {
    static class StackWithMin {
        void push(int value) { /* TODO */ }
        int pop() { return -1; /* TODO */ }
        int top() { return -1; /* TODO */ }
        int getMin() { return -1; /* TODO */ }
    }

    public static int solve(int[] values) {
        // TODO: mô phỏng push tất cả rồi trả về min hiện tại.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("minimum after pushes", solve(new int[] { -2, 0, -3 }), -3);
        Check.summary();
    }
}
