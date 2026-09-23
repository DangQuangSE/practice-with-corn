/**
 * Bài 11.10 — Maximum XOR for Each Query
 * Difficulty: Medium
 */
public class MaximumXorForEachQuery {
    public static int[] solve(int[] nums, int maximumBit) {
        // TODO: prefix XOR và chọn mask để kết quả đạt lớn nhất.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("xor queries",
                solve(new int[] { 0, 1, 1, 3 }, 2),
                new int[] { 0, 3, 2, 3 });
        Check.summary();
    }
}
