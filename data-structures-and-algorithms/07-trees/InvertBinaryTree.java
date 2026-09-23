/**
 * Bài 7.3 — Đảo cây nhị phân
 * Difficulty: Easy
 */
public class InvertBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode solve(TreeNode root) {
        // TODO: đổi left/right sau khi hoặc trước khi xử lý hai cây con.
        return null;
    }

    public static void main(String[] args) {
        Check.expect("empty tree", solve(null), null);
        Check.summary();
    }
}
