/**
 * Bài 5.2 — Đảo ngược chuỗi bằng đệ quy
 * Difficulty: Easy
 */
public class ReverseStringRecursive {
    public static String solve(String s) {
        // TODO: base case chuỗi rỗng; tách ký tự đầu và phần còn lại.
        return "";
    }

    public static void main(String[] args) {
        Check.expect("reverse", solve("hello"), "olleh");
        Check.expect("empty", solve(""), "");
        Check.summary();
    }
}
