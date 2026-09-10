/**
 * Bài 1.9 — Trapping Rain Water
 *
 * Cho mảng độ cao các cột, tính tổng lượng nước mưa giữ lại được giữa các cột.
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]  ->  Output: 6
 *
 * Ý tưởng: nước giữ được tại vị trí i = min(maxLeft[i], maxRight[i]) - height[i].
 * Mục tiêu: bắt đầu bằng cách dùng 2 mảng phụ maxLeft/maxRight (O(n) bộ nhớ),
 * sau đó tối ưu xuống O(1) bộ nhớ bằng 2 con trỏ trái/phải.
 *
 * Đây là bài khó nhất chủ đề này và là bài kinh điển trong phỏng vấn.
 */
public class TrappingRainWater {

    public static int solve(int[] height) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("TrappingRainWater");

        Check.expect("basic example",
                solve(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6);

        Check.expect("second example",
                solve(new int[] {4, 2, 0, 3, 2, 5}), 9);

        Check.expect("empty array", solve(new int[] {}), 0);

        Check.expect("single bar", solve(new int[] {5}), 0);

        Check.expect("two bars trap nothing", solve(new int[] {5, 5}), 0);

        Check.expect("smallest possible basin", solve(new int[] {2, 0, 2}), 2);

        Check.expect("increasing slope traps nothing",
                solve(new int[] {1, 2, 3, 4}), 0);

        Check.expect("decreasing slope traps nothing",
                solve(new int[] {4, 3, 2, 1}), 0);

        Check.expect("all bars equal height",
                solve(new int[] {3, 3, 3}), 0);

        Check.expect("zero-height bars at both ends",
                solve(new int[] {0, 2, 0, 2, 0}), 2);

        Check.summary();
    }
}
