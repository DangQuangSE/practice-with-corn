/**
 * Bài 2.10 — Maximum Average Subarray I
 * Difficulty: Medium
 *
 * Tìm giá trị trung bình lớn nhất của một dãy con liên tiếp có đúng k phần tử.
 * Input: [1,12,-5,-6,50,3], k=4 -> 12.75
 * Mục tiêu: O(n) thời gian bằng sliding window kích thước cố định.
 */
public class MaximumAverageSubarray {

    public static double solve(int[] nums, int k) {
        // TODO: tính tổng cửa sổ đầu tiên rồi trừ phần tử cũ, cộng phần tử mới.
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println("MaximumAverageSubarray");

        Check.expect("basic example",
                solve(new int[] { 1, 12, -5, -6, 50, 3 }, 4),
                12.75);

        Check.expect("single element window", solve(new int[] { 5, 2 }, 1), 5.0);

        Check.summary();
    }
}
