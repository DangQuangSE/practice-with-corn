import java.util.PriorityQueue;

/**
 * Bài 8.10 — IPO / Maximize Capital
 * Difficulty: Medium
 *
 * Chọn tối đa k dự án, mỗi dự án cần capital và tạo profit; tối đa hóa vốn ban đầu.
 */
public class MaximizeCapital {
    public static int solve(int k, int initialCapital, int[] profits, int[] capital) {
        // TODO: sort theo vốn yêu cầu, rồi đưa dự án đủ điều kiện vào max-heap profit.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("choose two projects",
                solve(2, 0, new int[] { 1, 2, 3 }, new int[] { 0, 1, 1 }), 4);
        Check.summary();
    }
}
