import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Bài 10.10 — Word Break
 * Difficulty: Medium
 */
public class WordBreak {
    public static boolean solve(String s, List<String> wordDict) {
        // TODO: dp[i] đúng nếu có j<i: dp[j] đúng và s[j..i) nằm trong Set.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("can split", solve("leetcode", List.of("leet", "code")), true);
        Check.expect("cannot split", solve("catsandog", List.of("cats", "dog", "sand", "and", "cat")), false);
        Check.summary();
    }
}
