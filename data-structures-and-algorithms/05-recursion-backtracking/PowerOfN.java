/**
 * Bài 5.3 — Tính x^n bằng đệ quy nhanh
 * Difficulty: Easy
 */
public class PowerOfN {
    public static double solve(double x, int n) {
        // TODO: bình phương kết quả nửa bài toán để đạt O(log n).
        return 0.0;
    }

    public static void main(String[] args) {
        Check.expect("positive exponent", solve(2, 10), 1024.0);
        Check.expect("zero exponent", solve(5, 0), 1.0);
        Check.summary();
    }
}
