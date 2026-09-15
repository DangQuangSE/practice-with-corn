/**
 * Bài 1.8 — Product of Array Except Self
 *
 * result[i] = tích của tất cả phần tử TRỪ nums[i]. KHÔNG được dùng phép chia.
 * Input: [1,2,3,4] -> Output: [24,12,8,6]
 *
 * Mục tiêu: O(n) thời gian. Nâng cao: chỉ O(1) bộ nhớ phụ
 * (mảng kết quả không tính là bộ nhớ phụ).
 *
 * Chú ý các test có số 0 — đây chính là lý do đề cấm dùng phép chia.
 */
public class ProductExceptSelf {

        public static int[] solve(int[] nums) {
                // TODO: viết lời giải ở đây
                int[] result = new int[nums.length];
                int prefix = 1;
                for (int i = 0; i < nums.length; i++) {
                        result[i] = prefix;
                        prefix *= nums[i];
                }
                int suffix = 1;
                for (int i = nums.length - 1; i >= 0; i--) {
                        result[i] *= suffix;
                        suffix *= nums[i];
                }
                return result;
        }

        public static void main(String[] args) {
                System.out.println("ProductExceptSelf");

                Check.expect("basic example",
                                solve(new int[] { 1, 2, 3, 4 }),
                                new int[] { 24, 12, 8, 6 });

                Check.expect("two elements",
                                solve(new int[] { 2, 3 }),
                                new int[] { 3, 2 });

                Check.expect("exactly one zero",
                                solve(new int[] { 1, 0 }),
                                new int[] { 0, 1 });

                Check.expect("one zero mixed with negatives",
                                solve(new int[] { -1, 1, 0, -3, 3 }),
                                new int[] { 0, 0, 9, 0, 0 });

                Check.expect("two zeros - every result is 0",
                                solve(new int[] { 0, 0, 5 }),
                                new int[] { 0, 0, 0 });

                Check.expect("all negative",
                                solve(new int[] { -1, -2, -3 }),
                                new int[] { 6, 3, 2 });

                Check.expect("all ones",
                                solve(new int[] { 1, 1, 1 }),
                                new int[] { 1, 1, 1 });

                Check.summary();
        }
}
