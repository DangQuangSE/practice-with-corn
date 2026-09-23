/**
 * Bài 2.8 — Kiểm tra danh sách liên kết đối xứng
 * Difficulty: Medium
 */
public class PalindromeLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean solve(ListNode head) {
        // TODO: tìm giữa, đảo nửa sau, rồi so sánh hai nửa.
        return false;
    }

    static ListNode from(int... values) {
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.expect("palindrome", solve(from(1, 2, 2, 1)), true);
        Check.expect("not palindrome", solve(from(1, 2, 3)), false);
        Check.summary();
    }
}
