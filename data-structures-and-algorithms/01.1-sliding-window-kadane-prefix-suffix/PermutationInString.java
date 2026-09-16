/**
 * Bài 1.11 — Permutation in String
 *
 * Kiểm tra s2 có chứa một substring là hoán vị của s1 hay không.
 * Input: s1="ab", s2="eidbaooo" -> Output: true
 *
 * Quy ước: s1 và s2 chỉ gồm chữ cái thường tiếng Anh.
 * Mục tiêu: O(|s1| + |s2|) thời gian và O(1) bộ nhớ phụ.
 */
public class PermutationInString {

    public static boolean solve(String s1, String s2) {
        // TODO: viết lời giải ở đây
        return false;
    }

    public static void main(String[] args) {
        System.out.println("PermutationInString");

        Check.expect("basic example", solve("ab", "eidbaooo"), true);

        Check.expect("no permutation", solve("ab", "eidboaoo"), false);

        Check.expect("repeated characters", solve("aabc", "xxcaaby"), true);

        Check.expect("exact same string", solve("abc", "abc"), true);

        Check.expect("s1 longer than s2", solve("abcd", "abc"), false);

        Check.expect("single character match", solve("a", "xxa"), true);

        Check.expect("single character missing", solve("z", "abc"), false);

        Check.expect("window must move past an early match", solve("adc", "dcda"), true);

        Check.summary();
    }
}
