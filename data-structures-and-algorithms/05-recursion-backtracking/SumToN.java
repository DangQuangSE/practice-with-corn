/**
 * Bài 5.1 — Tính tổng 1..n bằng đệ quy
 * Difficulty: Easy
 */
public class SumToN {
    public static int solve(int n) {
        // TODO: base case sum(0)=0; recursive case n + solve(n-1).
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("sum one to five", solve(5), 15);
        Check.expect("base case", solve(0), 0);
        Check.summary();
    }
}
