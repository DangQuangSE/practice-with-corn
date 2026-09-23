import java.util.ArrayList;
import java.util.List;

/**
 * Bài 5.10 — Letter Combinations of a Phone Number
 * Difficulty: Medium
 */
public class LetterCombinationsOfPhoneNumber {
    public static List<String> solve(String digits) {
        // TODO: mỗi chữ số mở ra một nhánh lựa chọn ký tự.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("three digits", solve("23").size(), 9);
        Check.expect("empty input", solve("").size(), 0);
        Check.summary();
    }
}
