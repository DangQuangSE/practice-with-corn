/**
 * Bài 11.4 — Permutation in String
 * Difficulty: Medium
 */
public class PermutationInStringAdvanced {
    public static boolean solve(String s1, String s2) {
        // TODO: cửa sổ cố định bằng s1.length(), so sánh tần suất ký tự.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("permutation exists", solve("ab", "eidbaooo"), true);
        Check.expect("permutation absent", solve("ab", "eidboaoo"), false);
        Check.summary();
    }
}
