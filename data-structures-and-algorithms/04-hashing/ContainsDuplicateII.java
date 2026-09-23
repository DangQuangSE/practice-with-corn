import java.util.HashMap;
import java.util.Map;

/**
 * Bài 4.10 — Contains Duplicate II
 * Difficulty: Medium
 *
 * Kiểm tra có hai chỉ số i, j sao cho nums[i] == nums[j] và j - i <= k.
 */
public class ContainsDuplicateII {
    public static boolean solve(int[] nums, int k) {
        // TODO: lưu vị trí xuất hiện gần nhất của từng giá trị.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("near duplicate", solve(new int[] { 1, 2, 3, 1 }, 3), true);
        Check.expect("too far apart", solve(new int[] { 1, 2, 3, 1 }, 2), false);
        Check.summary();
    }
}
