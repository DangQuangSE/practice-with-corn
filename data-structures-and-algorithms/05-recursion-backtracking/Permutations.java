import java.util.ArrayList;
import java.util.List;

/**
 * Bài 5.5 — Sinh tất cả hoán vị
 * Difficulty: Medium
 */
public class Permutations {
    public static List<List<Integer>> solve(int[] nums) {
        // TODO: dùng used[] hoặc swap tại chỗ, rồi quay lui sau mỗi nhánh.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("permutation count", solve(new int[] { 1, 2, 3 }).size(), 6);
        Check.summary();
    }
}
