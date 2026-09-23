import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.3 — Đánh giá biểu thức hậu tố (Reverse Polish Notation)
 * Difficulty: Medium
 */
public class EvaluateReversePolishNotation {
    public static int solve(String[] tokens) {
        // TODO: số thì push; toán tử thì pop phải, pop trái, tính và push lại.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("basic expression",
                solve(new String[] { "2", "1", "+", "3", "*" }), 9);
        Check.expect("division truncates toward zero",
                solve(new String[] { "4", "13", "5", "/", "+" }), 6);
        Check.summary();
    }
}
