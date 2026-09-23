import java.util.ArrayList;
import java.util.List;

/**
 * Bài 7.6 — Path Sum II
 * Difficulty: Medium
 */
public class PathSumII {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<List<Integer>> solve(TreeNode root, int targetSum) {
        // TODO: DFS + backtracking; chỉ ghi nhận đường đi từ root tới leaf.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("no path yet", solve(null, 22).size(), 0);
        Check.summary();
    }
}
