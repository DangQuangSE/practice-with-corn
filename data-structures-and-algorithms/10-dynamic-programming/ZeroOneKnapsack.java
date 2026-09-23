/**
 * Bài 10.6 — 0/1 Knapsack
 * Difficulty: Medium
 */
public class ZeroOneKnapsack {
    public static int solve(int[] weights, int[] values, int capacity) {
        // TODO: mỗi vật chọn 0 hoặc 1 lần; thử dp 2D rồi tối ưu dp 1D.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("best value",
                solve(new int[] { 1, 2, 3 }, new int[] { 6, 10, 12 }, 5), 22);
        Check.summary();
    }
}
