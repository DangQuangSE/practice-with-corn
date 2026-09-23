/**
 * Bài 7.4 — Kiểm tra BST hợp lệ
 * Difficulty: Medium
 */
public class ValidateBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean solve(TreeNode root) {
        // TODO: truyền khoảng (min, max), không chỉ so sánh node với con trực tiếp.
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Check.expect("valid bst", solve(root), true);
        Check.summary();
    }
}
