/**
 * Bài 7.1 — Tính chiều cao cây nhị phân
 * Difficulty: Easy
 */
public class MaximumDepth {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int solve(TreeNode root) {
        // TODO: 1 + max(depth(left), depth(right)); null có depth 0.
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        Check.expect("tree depth", solve(root), 3);
        Check.summary();
    }
}
