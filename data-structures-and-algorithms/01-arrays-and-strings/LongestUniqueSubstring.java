/**
 * Bài 1.6 — Chuỗi con không lặp ký tự dài nhất
 *
 * Input: "abcabcbb"  ->  Output: 3 (chuỗi "abc")
 *
 * Trả về ĐỘ DÀI, không phải chuỗi.
 * Mục tiêu: O(n) thời gian bằng sliding window.
 *
 * Chú ý 2 test "dvdf" và "abba" — đây là chỗ lời giải sai hay lộ ra:
 * khi gặp ký tự trùng, con trỏ trái chỉ được phép TIẾN, không được lùi lại phía sau.
 */
public class LongestUniqueSubstring {

    public static int solve(String s) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("LongestUniqueSubstring");

        Check.expect("basic example", solve("abcabcbb"), 3);

        Check.expect("all chars identical", solve("bbbbb"), 1);

        Check.expect("pwwkew", solve("pwwkew"), 3);

        Check.expect("empty string", solve(""), 0);

        Check.expect("single char", solve("a"), 1);

        Check.expect("no repeats at all", solve("abcdef"), 6);

        Check.expect("single space", solve(" "), 1);

        Check.expect("dvdf - left pointer must not move back", solve("dvdf"), 3);

        Check.expect("abba - left pointer must not move back", solve("abba"), 2);

        Check.expect("digits and symbols", solve("a1!a1!"), 3);

        Check.summary();
    }
}
