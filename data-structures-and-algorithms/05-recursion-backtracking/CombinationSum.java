import java.util.ArrayList;
import java.util.List;

/**
 * Bài 5.6 — Combination Sum
 * Difficulty: Medium
 */
public class CombinationSum {
    public static List<List<Integer>> solve(int[] candidates, int target) {
        // TODO: sort, backtrack, cho phép dùng lại phần tử tại cùng index.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("two combinations",
                solve(new int[] { 2, 3, 6, 7 }, 7).size(), 2);
        Check.summary();
    }
}
