/**
 * Bài 2.1 — Tính độ dài danh sách liên kết
 * Difficulty: Easy
 */
public class LinkedListLength {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static int solve(ListNode head) {
        // TODO: duyệt từ head tới null và đếm node.
        return 0;
    }

    static ListNode from(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Check.expect("three nodes", solve(from(1, 2, 3)), 3);
        Check.expect("empty list", solve(null), 0);
        Check.summary();
    }
}
