import java.util.HashMap;
import java.util.Map;

/**
 * Bài 4.2 — Kiểm tra hai chuỗi là Anagram
 * Difficulty: Easy
 */
public class ValidAnagram {
    public static boolean solve(String first, String second) {
        // TODO: đếm ký tự của first rồi giảm khi duyệt second.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("anagram", solve("listen", "silent"), true);
        Check.expect("different counts", solve("rat", "car"), false);
        Check.summary();
    }
}
