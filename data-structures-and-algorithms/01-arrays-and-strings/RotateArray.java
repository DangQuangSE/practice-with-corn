/**
 * Bài 1.5 — Xoay mảng sang phải k bước
 *
 * Input: nums=[1,2,3,4,5,6,7], k=3 -> [5,6,7,1,2,3,4]
 *
 * Lưu ý: hàm sửa mảng tại chỗ (in-place), không trả về gì — giống đề gốc trên
 * LeetCode.
 * k có thể lớn hơn độ dài mảng, nhớ xử lý phần dư.
 * Mục tiêu: O(n) thời gian, O(1) bộ nhớ phụ (đảo ngược toàn mảng rồi đảo từng
 * nửa).
 */
public class RotateArray {

    public static void solve(int[] nums, int k) {
        // TODO: viết lời giải ở đây
        int size = nums.length;
        if (size == 0)
            return;
        k = k % size;
        if (k == 0)
            return;
        reverse(nums, 0, size - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, size - 1);
    }

    public static void reverse(int[] nums, int left, int right) {
        while (left <= right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        System.out.println("RotateArray");

        int[] a = { 1, 2, 3, 4, 5, 6, 7 };
        solve(a, 3);
        Check.expect("basic example, k=3", a, new int[] { 5, 6, 7, 1, 2, 3, 4 });

        int[] b = { 1, 2, 3, 4, 5 };
        solve(b, 0);
        Check.expect("k=0 leaves array unchanged", b, new int[] { 1, 2, 3, 4, 5 });

        int[] c = { 1, 2, 3, 4, 5 };
        solve(c, 5);
        Check.expect("k equals length, unchanged", c, new int[] { 1, 2, 3, 4, 5 });

        int[] d = { 1, 2, 3, 4, 5, 6, 7 };
        solve(d, 10);
        Check.expect("k larger than length (10 % 7 = 3)", d, new int[] { 5, 6, 7, 1, 2, 3, 4 });

        int[] e = { 1 };
        solve(e, 3);
        Check.expect("single element", e, new int[] { 1 });

        int[] f = { 1, 2 };
        solve(f, 1);
        Check.expect("two elements, k=1", f, new int[] { 2, 1 });

        int[] g = {};
        solve(g, 2);
        Check.expect("empty array", g, new int[] {});

        int[] h = { -1, -100, 3, 99 };
        solve(h, 2);
        Check.expect("negative numbers", h, new int[] { 3, 99, -1, -100 });

        Check.summary();
    }
}
