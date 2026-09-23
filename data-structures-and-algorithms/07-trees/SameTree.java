/**
 * Bài 7.2 — Kiểm tra hai cây giống nhau
 * Difficulty: Easy
 */
public class SameTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean solve(TreeNode first, TreeNode second) {
        // TODO: so sánh null, giá trị, rồi đệ quy hai cây con.
        return false;
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        TreeNode second = new TreeNode(1);
        second.left = new TreeNode(2);
        Check.expect("same structure", solve(first, second), true);
        Check.summary();
    }
}
