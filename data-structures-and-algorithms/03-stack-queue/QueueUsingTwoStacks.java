import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.2 — Triển khai Queue bằng 2 Stack
 * Difficulty: Easy
 */
public class QueueUsingTwoStacks {
    static class MyQueue {
        private final Deque<Integer> input = new ArrayDeque<>();
        private final Deque<Integer> output = new ArrayDeque<>();

        void offer(int value) {
            // TODO
        }

        int poll() {
            // TODO
            return -1;
        }

        private void moveIfNeeded() {
            // TODO: chỉ chuyển input sang output khi output đang rỗng.
        }
    }

    public static int[] solve(int[] values) {
        // TODO: enqueue toàn bộ values, rồi dequeue theo thứ tự FIFO.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("FIFO order", solve(new int[] { 1, 2, 3 }), new int[] { 1, 2, 3 });
        Check.summary();
    }
}
