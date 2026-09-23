/**
 * Bài 2.4 — Phát hiện chu trình bằng Floyd's Algorithm
 * Difficulty: Medium
 */
public class LinkedListCycle {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean solve(ListNode head) {
        // TODO: slow đi 1 bước, fast đi 2 bước.
        return false;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        a.next = b; b.next = c; c.next = b;

        Check.expect("has cycle", solve(a), true);
        Check.expect("no cycle", solve(new ListNode(1)), false);
        Check.summary();
    }
}
