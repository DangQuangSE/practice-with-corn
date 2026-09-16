/**
 * Bài 1.12 — Longest Repeating Character Replacement
 *
 * Với tối đa k lần thay ký tự, tìm độ dài substring dài nhất có thể biến thành
 * toàn cùng một ký tự.
 * Input: s="AABABBA", k=1 -> Output: 4
 *
 * Quy ước: s chỉ gồm chữ cái hoa tiếng Anh và k >= 0.
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ bằng sliding window.
 */
public class LongestRepeatingCharacterReplacement {

    public static int solve(String s, int k) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("LongestRepeatingCharacterReplacement");

        Check.expect("basic example", solve("AABABBA", 1), 4);

        Check.expect("replace two characters", solve("ABAB", 2), 4);

        Check.expect("no replacement allowed", solve("ABCDE", 0), 1);

        Check.expect("all characters already equal", solve("AAAA", 0), 4);

        Check.expect("one character", solve("Z", 3), 1);

        Check.expect("k larger than required", solve("ABC", 5), 3);

        Check.expect("best window is after a shrink", solve("BAAAB", 1), 4);

        Check.expect("empty string", solve("", 2), 0);

        Check.summary();
    }
}
