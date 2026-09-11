/**
 * Bài 1.2 — Kiểm tra chuỗi đối xứng (Palindrome)
 *
 * Input: "madam" -> true; "hello" -> false
 *
 * Quy ước ở đây (mức nâng cao): bỏ qua hoa/thường và mọi ký tự không phải
 * chữ/số.
 * Nên "A man a plan a canal Panama" -> true.
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ (dùng 2 con trỏ, không tạo chuỗi
 * mới).
 */
public class Palindrome {

    public static boolean solve(String s) {
        // TODO: viết lời giải ở đây
        if (s.length() <= 1)
            return true;
        int mid = s.length() / 2;
        int left = 0;
        int right = s.length() - 1;
        while (left < mid) {
            if (!Character.isLetterOrDigit(s.charAt((left)))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (!(Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right)))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Palindrome");

        Check.expect("madam", solve("madam"), true);

        Check.expect("hello", solve("hello"), false);

        Check.expect("ignores case and spaces",
                solve("A man a plan a canal Panama"), true);

        Check.expect("ignores punctuation",
                solve("race a car"), false);

        Check.expect("empty string", solve(""), true);

        Check.expect("single char", solve("a"), true);

        Check.expect("two different chars", solve("ab"), false);

        Check.expect("two chars differing only in case", solve("Ab"), false);

        Check.expect("even length", solve("abba"), true);

        Check.expect("digits only", solve("12321"), true);

        Check.expect("punctuation only", solve(".,121"), true);

        Check.summary();
    }
}
