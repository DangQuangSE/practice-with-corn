/**
 * Bài 1.3 — Đảo ngược một chuỗi
 *
 * Input: "corn"  ->  Output: "nroc"
 *
 * Gợi ý: StringBuilder.reverse() là cách nhanh, nhưng hãy tự code bằng 2 con trỏ
 * trên char[] để hiểu bản chất — phỏng vấn thường yêu cầu cách thứ hai.
 * Mục tiêu: O(n) thời gian.
 */
public class ReverseString {

    public static String solve(String s) {
        // TODO: viết lời giải ở đây
        return "";
    }

    public static void main(String[] args) {
        System.out.println("ReverseString");

        Check.expect("basic example", solve("corn"), "nroc");

        Check.expect("empty string", solve(""), "");

        Check.expect("single char", solve("a"), "a");

        Check.expect("odd length", solve("abc"), "cba");

        Check.expect("even length", solve("abcd"), "dcba");

        Check.expect("palindrome stays the same", solve("level"), "level");

        Check.expect("contains a space", solve("a b"), "b a");

        Check.summary();
    }
}
