/**
 * Bài 1.4 — Two Sum
 *
 * Cho mảng nums và số target, tìm 2 chỉ số i, j sao cho nums[i] + nums[j] == target.
 * Input: nums=[2,7,11,15], target=9  ->  Output: [0,1]
 *
 * Mục tiêu: đạt O(n) thời gian.
 */
public class TwoSum {

    public static int[] solve(int[] nums, int target) {
        // TODO: viết lời giải ở đây
        return new int[] {};
    }

    public static void main(String[] args) {
        System.out.println("TwoSum");

        Check.expect("basic example",
                solve(new int[] {2, 7, 11, 15}, 9),
                new int[] {0, 1});

        Check.expect("pair at end of array",
                solve(new int[] {3, 2, 4}, 6),
                new int[] {1, 2});

        Check.expect("duplicate elements",
                solve(new int[] {3, 3}, 6),
                new int[] {0, 1});

        Check.expect("negative numbers",
                solve(new int[] {-1, -2, -3, -4}, -6),
                new int[] {1, 3});

        Check.expect("no pair found",
                solve(new int[] {1, 2, 3}, 100),
                new int[] {});

        Check.summary();
    }
}
