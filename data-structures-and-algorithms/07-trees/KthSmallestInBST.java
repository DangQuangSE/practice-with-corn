/**
 * Bài 7.10 — Kth Smallest Element in a BST
 * Difficulty: Medium
 */
public class KthSmallestInBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static int solve(TreeNode root, int k) {
        // TODO: inorder traversal của BST trả về các giá trị theo thứ tự tăng.
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        Check.expect("second smallest", solve(root, 2), 2);
        Check.summary();
    }
}
