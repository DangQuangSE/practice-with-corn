/**
 * Bài 2.10 — Tìm giao điểm của hai danh sách liên kết
 * Difficulty: Medium
 */
public class IntersectionOfTwoLinkedLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode solve(ListNode first, ListNode second) {
        // TODO: cho hai con trỏ đổi đầu danh sách khi chạm null.
        return null;
    }

    public static void main(String[] args) {
        ListNode shared = new ListNode(8);
        shared.next = new ListNode(10);
        ListNode first = new ListNode(3);
        first.next = new ListNode(7);
        first.next.next = shared;
        ListNode second = new ListNode(99);
        second.next = shared;

        Check.expect("has intersection", solve(first, second), shared);
        Check.expect("no intersection", solve(new ListNode(1), new ListNode(2)), null);
        Check.summary();
    }
}
