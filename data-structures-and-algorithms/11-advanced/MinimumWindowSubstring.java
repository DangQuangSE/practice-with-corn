import java.util.HashMap;
import java.util.Map;

/**
 * Bài 11.3 — Minimum Window Substring
 * Difficulty: Medium
 */
public class MinimumWindowSubstring {
    public static String solve(String s, String t) {
        // TODO: mở rộng để đủ t, rồi thu hẹp left nhiều nhất có thể.
        return "";
    }

    public static void main(String[] args) {
        Check.expect("smallest covering window",
                solve("ADOBECODEBANC", "ABC"), "BANC");
        Check.summary();
    }
}
