/**
 * Bài 1.1 — Tìm giá trị lớn nhất và nhỏ nhất trong mảng
 *
 * Input: [3, 7, 1, 9, 4] -> Output: max=9, min=1
 *
 * Quy ước ở đây: trả về mảng {max, min}. Mảng rỗng -> trả về mảng rỗng.
 * Mục tiêu: O(n) thời gian, chỉ duyệt mảng đúng 1 lần.
 */
public class MaxMin {

        public static int[] solve(int[] nums) {
                // TODO: viết lời giải ở đây
                // step 1: block num is empty
                if (nums.length == 0)
                        return new int[] {};
                int min = nums[0];
                int max = nums[0];
                for (int i = 1; i < nums.length; i++) {
                        if (nums[i] < min) {
                                min = nums[i];
                        }
                        if (nums[i] > max) {
                                max = nums[i];
                        }
                }
                return new int[] { max, min };
        }

        public static void main(String[] args) {
                System.out.println("MaxMin");

                Check.expect("basic example",
                                solve(new int[] { 3, 7, 1, 9, 4 }),
                                new int[] { 9, 1 });

                Check.expect("single element",
                                solve(new int[] { 5 }),
                                new int[] { 5, 5 });

                Check.expect("all elements equal",
                                solve(new int[] { 2, 2, 2 }),
                                new int[] { 2, 2 });

                Check.expect("all negative",
                                solve(new int[] { -3, -1, -7 }),
                                new int[] { -1, -7 });

                Check.expect("mixed signs and zero",
                                solve(new int[] { 0, -5, 10 }),
                                new int[] { 10, -5 });

                Check.expect("max first, min last",
                                solve(new int[] { 9, 5, 3, 1 }),
                                new int[] { 9, 1 });

                Check.expect("empty array",
                                solve(new int[] {}),
                                new int[] {});

                Check.summary();
        }
}
