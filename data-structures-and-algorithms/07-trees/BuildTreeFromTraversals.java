import java.util.HashMap;
import java.util.Map;

/**
 * Bài 7.7 — Xây cây từ Preorder và Inorder
 * Difficulty: Medium
 */
public class BuildTreeFromTraversals {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static TreeNode solve(int[] preorder, int[] inorder) {
        // TODO: root là phần tử đầu preorder; HashMap giúp tìm vị trí trong inorder.
        return null;
    }

    public static void main(String[] args) {
        Check.expect("empty traversals", solve(new int[0], new int[0]), null);
        Check.summary();
    }
}
