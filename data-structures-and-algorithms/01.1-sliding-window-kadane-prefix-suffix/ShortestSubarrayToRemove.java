/**
 * Bài 1.18 — Shortest Subarray to Remove to Make Array Sorted
 *
 * Xóa đúng một đoạn liên tiếp (đoạn rỗng được phép) để phần còn lại không giảm.
 * Trả về độ dài nhỏ nhất của đoạn cần xóa.
 * Input: [1,2,3,10,4,2,3,5] -> Output: 3
 *
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ. Kết hợp đoạn tăng đầu, đoạn tăng
 * cuối, rồi nối hai đoạn bằng hai con trỏ.
 */
public class ShortestSubarrayToRemove {

    public static int solve(int[] nums) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("ShortestSubarrayToRemove");

        Check.expect("basic example",
                solve(new int[] { 1, 2, 3, 10, 4, 2, 3, 5 }), 3);

        Check.expect("remove one middle element",
                solve(new int[] { 1, 2, 6, 3, 4, 5 }), 1);

        Check.expect("already non-decreasing",
                solve(new int[] { 1, 2, 3, 3, 5 }), 0);

        Check.expect("strictly decreasing", solve(new int[] { 5, 4, 3, 2, 1 }), 4);

        Check.expect("single element", solve(new int[] { 7 }), 0);

        Check.expect("remove one middle element with duplicates",
                solve(new int[] { 1, 2, 3, 3, 10, 0, 11 }), 1);

        Check.expect("duplicate values can stay",
                solve(new int[] { 1, 1, 1, 1 }), 0);

        Check.summary();
    }
}
