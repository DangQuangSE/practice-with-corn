/**
 * Bài 1.7 — Maximum Subarray (thuật toán Kadane)
 *
 * Tìm tổng lớn nhất của một dãy con LIÊN TIẾP (không rỗng).
 * Input: [-2,1,-3,4,-1,2,1,-5,4] -> Output: 6 (dãy [4,-1,2,1])
 *
 * Ràng buộc: mảng có ít nhất 1 phần tử.
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ.
 *
 * Chú ý test "all negative": khởi tạo maxSum = 0 sẽ cho kết quả sai,
 * vì dãy con phải có ít nhất 1 phần tử.
 */
public class MaxSubarray {

        public static int solve(int[] nums) {
                // TODO: viết lời giải ở đây
                int current = nums[0];
                int max = nums[0];
                for (int i = 1; i < nums.length; i++) {
                        current = Math.max(nums[i], current + nums[i]);
                        max = Math.max(max, current);
                }
                return max;
        }

        public static void main(String[] args) {
                System.out.println("MaxSubarray");

                Check.expect("basic example",
                                solve(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }), 6);

                Check.expect("single positive element", solve(new int[] { 1 }), 1);

                Check.expect("single negative element", solve(new int[] { -1 }), -1);

                Check.expect("all positive, take whole array",
                                solve(new int[] { 1, 2, 3, 4 }), 10);

                Check.expect("all negative, take largest element",
                                solve(new int[] { -3, -1, -2 }), -1);

                Check.expect("bridge over a negative in the middle",
                                solve(new int[] { 5, 4, -1, 7, 8 }), 23);

                Check.expect("small negative worth crossing",
                                solve(new int[] { 2, -1, 2 }), 3);

                Check.expect("large negative not worth crossing",
                                solve(new int[] { 2, -5, 3 }), 3);

                Check.expect("contains zero", solve(new int[] { -1, 0, -2 }), 0);

                Check.summary();
        }
}
