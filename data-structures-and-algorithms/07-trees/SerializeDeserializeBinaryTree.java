import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Bài 7.9 — Serialize và Deserialize Binary Tree
 * Difficulty: Medium
 */
public class SerializeDeserializeBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public static String serialize(TreeNode root) {
        // TODO: preorder với token null rõ ràng, ví dụ #.
        return "";
    }

    public static TreeNode deserialize(String data) {
        // TODO: đọc token theo đúng thứ tự serialize.
        return null;
    }

    public static void main(String[] args) {
        Check.expect("null tree", serialize(null), "#");
        Check.summary();
    }
}
