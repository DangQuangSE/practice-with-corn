/**
 * Bài 1.15 — Maximum Subarray Sum with One Deletion
 *
 * Tìm tổng lớn nhất của dãy con liên tiếp sau khi được phép xóa tối đa một phần
 * tử. Dãy con sau cùng vẫn phải không rỗng.
 * Input: nums=[1,-2,0,3] -> Output: 4 (xóa -2)
 *
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ. Đây là Kadane với hai trạng thái:
 * chưa xóa và đã xóa một phần tử.
 */
public class MaximumSubarrayOneDeletion {

    public static int solve(int[] nums) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("MaximumSubarrayOneDeletion");

        Check.expect("delete a negative middle value",
                solve(new int[] { 1, -2, 0, 3 }), 4);

        Check.expect("deletion is optional",
                solve(new int[] { 1, 2, 3, 4 }), 10);

        Check.expect("all negative values",
                solve(new int[] { -1, -1, -1 }), -1);

        Check.expect("single element", solve(new int[] { 5 }), 5);

        Check.expect("delete the only large negative",
                solve(new int[] { 1, -2, 3, 4 }), 8);

        Check.expect("best result may keep one element",
                solve(new int[] { -5, -2, -8 }), -2);

        Check.expect("zero can be deleted or kept",
                solve(new int[] { 0, -1, 0, -2 }), 0);

        Check.summary();
    }
}
