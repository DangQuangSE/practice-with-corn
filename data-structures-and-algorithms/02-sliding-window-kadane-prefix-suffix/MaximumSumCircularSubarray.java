/**
 * Bài 1.14 — Maximum Sum Circular Subarray
 *
 * Tìm tổng lớn nhất của dãy con liên tiếp trong mảng vòng. Dãy con không rỗng
 * và có thể nối từ cuối mảng về đầu mảng.
 * Input: nums=[5,-3,5] -> Output: 10
 *
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ. Đây là biến thể Kadane.
 */
public class MaximumSumCircularSubarray {

    public static int solve(int[] nums) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("MaximumSumCircularSubarray");

        Check.expect("non-circular maximum", solve(new int[] { 1, -2, 3, -2 }), 3);

        Check.expect("wrap around the end and start",
                solve(new int[] { 5, -3, 5 }), 10);

        Check.expect("all negative values",
                solve(new int[] { -3, -2, -3 }), -2);

        Check.expect("wrap around is better",
                solve(new int[] { 3, -1, 2, -1 }), 4);

        Check.expect("single element", solve(new int[] { 7 }), 7);

        Check.expect("contains zero", solve(new int[] { 0, -1, 0 }), 0);

        Check.expect("all positive values",
                solve(new int[] { 1, 2, 3, 4 }), 10);

        Check.summary();
    }
}
