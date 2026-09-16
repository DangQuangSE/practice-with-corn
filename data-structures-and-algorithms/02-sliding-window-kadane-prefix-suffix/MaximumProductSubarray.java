/**
 * Bài 1.13 — Maximum Product Subarray
 *
 * Tìm tích lớn nhất của một dãy con liên tiếp không rỗng.
 * Input: nums=[2,3,-2,4] -> Output: 6
 *
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ.
 * Gợi ý: ngoài tích lớn nhất kết thúc tại vị trí hiện tại, phải giữ cả tích nhỏ
 * nhất vì số âm có thể biến nó thành tích lớn nhất ở bước sau.
 */
public class MaximumProductSubarray {

    public static int solve(int[] nums) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("MaximumProductSubarray");

        Check.expect("basic example", solve(new int[] { 2, 3, -2, 4 }), 6);

        Check.expect("two negatives make a positive",
                solve(new int[] { -2, 3, -4 }), 24);

        Check.expect("zero splits the array",
                solve(new int[] { -2, 0, -1 }), 0);

        Check.expect("single negative", solve(new int[] { -2 }), -2);

        Check.expect("single positive after zero",
                solve(new int[] { 0, 2 }), 2);

        Check.expect("negative pair is the best product",
                solve(new int[] { -2, -3, -4 }), 6);

        Check.expect("negative prefix and suffix",
                solve(new int[] { 1, -2, -3, 4 }), 24);

        Check.summary();
    }
}
