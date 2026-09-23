import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.1 — Kiểm tra ngoặc hợp lệ
 * Difficulty: Easy
 */
public class ValidParentheses {
    public static boolean solve(String s) {
        // TODO: push ngoặc mở, pop và đối chiếu khi gặp ngoặc đóng.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("nested brackets", solve("({[]})"), true);
        Check.expect("mismatched brackets", solve("(]"), false);
        Check.summary();
    }
}
