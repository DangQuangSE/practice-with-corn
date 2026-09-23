/**
 * Bài 10.9 — Partition Equal Subset Sum
 * Difficulty: Medium
 */
public class PartitionEqualSubsetSum {
    public static boolean solve(int[] nums) {
        // TODO: nếu tổng chẵn, kiểm tra subset sum bằng total/2 bằng dp boolean.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("can partition", solve(new int[] { 1, 5, 11, 5 }), true);
        Check.expect("cannot partition", solve(new int[] { 1, 2, 3, 5 }), false);
        Check.summary();
    }
}
