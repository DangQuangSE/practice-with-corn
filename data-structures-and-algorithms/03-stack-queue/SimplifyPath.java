import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.10 — Simplify Path
 * Difficulty: Medium
 */
public class SimplifyPath {
    public static String solve(String path) {
        // TODO: dùng stack các thư mục; bỏ ".", xử lý "..", bỏ token rỗng.
        return "";
    }

    public static void main(String[] args) {
        Check.expect("parent directory",
                solve("/home//foo/"), "/home/foo");
        Check.expect("root cannot go up",
                solve("/../"), "/");
        Check.summary();
    }
}
