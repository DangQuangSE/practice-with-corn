/**
 * Bài 8.2 — Kiểm tra mảng là Min-Heap hợp lệ
 * Difficulty: Easy
 */
public class ValidMinHeap {
    public static boolean solve(int[] heap) {
        // TODO: với node i, kiểm tra heap[i] <= các con 2*i+1 và 2*i+2.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("valid heap", solve(new int[] { 1, 3, 2, 7, 6 }), true);
        Check.expect("invalid heap", solve(new int[] { 3, 1, 2 }), false);
        Check.summary();
    }
}
