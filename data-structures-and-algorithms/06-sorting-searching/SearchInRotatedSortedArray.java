/**
 * Bài 6.4 — Tìm kiếm trong mảng xoay
 * Difficulty: Medium
 */
public class SearchInRotatedSortedArray {
    public static int solve(int[] nums, int target) {
        // TODO: mỗi bước một nửa vẫn được sắp xếp; chọn nửa phù hợp.
        return -1;
    }

    public static void main(String[] args) {
        Check.expect("rotated search",
                solve(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0), 4);
        Check.summary();
    }
}
