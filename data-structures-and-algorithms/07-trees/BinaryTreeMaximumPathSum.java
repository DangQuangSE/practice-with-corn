/**
 * Bài 7.8 — Binary Tree Maximum Path Sum
 * Difficulty: Medium
 */
public class BinaryTreeMaximumPathSum {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int solve(TreeNode root) {
        // TODO: gain của một nhánh và đáp án toàn cục có thể dùng cả hai nhánh.
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Check.expect("best path", solve(root), 42);
        Check.summary();
    }
}
