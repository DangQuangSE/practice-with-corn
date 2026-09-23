/**
 * Bài 7.5 — Lowest Common Ancestor trong BST
 * Difficulty: Medium
 */
public class LowestCommonAncestorBST {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static Integer solve(TreeNode root, int first, int second) {
        // TODO: dựa vào việc hai giá trị nằm cùng bên trái/phải của root.
        return null;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        Check.expect("ancestor is root", solve(root, 2, 8), 6);
        Check.summary();
    }
}
