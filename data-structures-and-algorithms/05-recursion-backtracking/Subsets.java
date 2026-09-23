import java.util.ArrayList;
import java.util.List;

/**
 * Bài 5.4 — Sinh tất cả tập con
 * Difficulty: Medium
 */
public class Subsets {
    public static List<List<Integer>> solve(int[] nums) {
        // TODO: mỗi phần tử có hai lựa chọn: chọn hoặc bỏ.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("power set size", solve(new int[] { 1, 2, 3 }).size(), 8);
        Check.summary();
    }
}
