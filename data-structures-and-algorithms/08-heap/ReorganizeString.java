import java.util.PriorityQueue;

/**
 * Bài 8.9 — Reorganize String
 * Difficulty: Medium
 *
 * Sắp xếp lại ký tự sao cho hai ký tự kề nhau không giống nhau; không thể thì trả "".
 */
public class ReorganizeString {
    public static String solve(String s) {
        // TODO: luôn lấy hai ký tự còn nhiều nhất từ max-heap.
        return "";
    }

    public static void main(String[] args) {
        Check.expect("reorganize possible", solve("aab").length(), 3);
        Check.expect("impossible", solve("aaab"), "");
        Check.summary();
    }
}
