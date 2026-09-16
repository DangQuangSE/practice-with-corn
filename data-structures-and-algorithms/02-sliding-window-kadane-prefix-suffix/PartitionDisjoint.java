/**
 * Bài 1.17 — Partition Array into Disjoint Intervals
 *
 * Chia nums thành left và right, cả hai không rỗng, sao cho mọi phần tử của
 * left <= mọi phần tử của right. Trả về độ dài nhỏ nhất của left.
 * Input: [5,0,3,8,6] -> Output: 3
 *
 * Mục tiêu: O(n) thời gian. Gợi ý: prefix maximum và suffix minimum.
 */
public class PartitionDisjoint {

    public static int solve(int[] nums) {
        // TODO: viết lời giải ở đây
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("PartitionDisjoint");

        Check.expect("basic example",
                solve(new int[] { 5, 0, 3, 8, 6 }), 3);

        Check.expect("partition after repeated small values",
                solve(new int[] { 1, 1, 1, 0, 6, 12 }), 4);

        Check.expect("already partitioned", solve(new int[] { 1, 2, 3, 4 }), 1);

        Check.expect("two elements", solve(new int[] { 2, 2 }), 1);

        Check.expect("descending values", solve(new int[] { 5, 4, 3, 2, 1 }), 4);

        Check.expect("negative values", solve(new int[] { -5, -4, -3, -10, 0 }), 4);

        Check.summary();
    }
}
