import java.util.PriorityQueue;

/**
 * Bài 8.1 — Tìm K phần tử nhỏ nhất
 * Difficulty: Easy
 */
public class KSmallestElements {
    public static int[] solve(int[] nums, int k) {
        // TODO: max-heap kích thước k; đỉnh là phần tử lớn nhất trong nhóm đang giữ.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("three smallest",
                solve(new int[] { 7, 10, 4, 3, 20, 15 }, 3),
                new int[] { 3, 4, 7 });
        Check.summary();
    }
}
