import java.util.ArrayList;
import java.util.List;

/**
 * Bài 11.8 — Accounts Merge
 * Difficulty: Medium
 */
public class AccountsMerge {
    public static List<List<String>> solve(List<List<String>> accounts) {
        // TODO: union các account có chung email, rồi gom email theo root.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("empty accounts", solve(new ArrayList<>()).size(), 0);
        Check.summary();
    }
}
