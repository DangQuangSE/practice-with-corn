/**
 * Bài 3.8 — Thiết kế Circular Queue
 * Difficulty: Medium
 */
public class CircularQueue {
    static class MyCircularQueue {
        private final int[] data;
        private int head;
        private int count;

        MyCircularQueue(int capacity) {
            data = new int[capacity];
        }

        boolean enQueue(int value) { return false; /* TODO */ }
        boolean deQueue() { return false; /* TODO */ }
        int Front() { return -1; /* TODO */ }
        int Rear() { return -1; /* TODO */ }
        boolean isEmpty() { return count == 0; }
        boolean isFull() { return count == data.length; }
    }

    public static int[] solve(int capacity, int[] values) {
        // TODO: enqueue values, rồi đọc lại queue theo thứ tự.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("queue order", solve(3, new int[] { 1, 2, 3 }), new int[] { 1, 2, 3 });
        Check.summary();
    }
}
