/**
 * Bài 6.1 — Tự cài Binary Search
 * Difficulty: Easy
 */
public class BinarySearch {
    public static int solve(int[] nums, int target) {
        // TODO: giữ left/right và loại bỏ một nửa không thể chứa target.
        return -1;
    }

    public static void main(String[] args) {
        Check.expect("found", solve(new int[] { 1, 3, 5, 7, 9 }, 7), 3);
        Check.expect("not found", solve(new int[] { 1, 3, 5 }, 4), -1);
        Check.summary();
    }
}
