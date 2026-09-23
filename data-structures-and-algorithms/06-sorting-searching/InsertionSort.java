/**
 * Bài 6.2 — Tự cài Insertion Sort
 * Difficulty: Easy
 */
public class InsertionSort {
    public static int[] solve(int[] nums) {
        // TODO: chèn từng phần tử vào prefix đã sắp xếp.
        return nums;
    }

    public static void main(String[] args) {
        Check.expect("sort values",
                solve(new int[] { 5, 2, 4, 1, 3 }),
                new int[] { 1, 2, 3, 4, 5 });
        Check.summary();
    }
}
