/**
 * Bài 6.6 — Dutch National Flag / Sort Colors
 * Difficulty: Medium
 */
public class SortColors {
    public static int[] solve(int[] nums) {
        // TODO: dùng low, mid, high để chia vùng 0, 1, 2 trong một lượt.
        return nums;
    }

    public static void main(String[] args) {
        Check.expect("three colors",
                solve(new int[] { 2, 0, 2, 1, 1, 0 }),
                new int[] { 0, 0, 1, 1, 2, 2 });
        Check.summary();
    }
}
