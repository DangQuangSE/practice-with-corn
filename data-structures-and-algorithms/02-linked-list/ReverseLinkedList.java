/**
 * Bài 2.2 — Đảo ngược danh sách liên kết
 * Difficulty: Easy
 */
public class ReverseLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        public String toString() {
            return val + (next == null ? "" : "->" + next);
        }
    }

    public static ListNode solve(ListNode head) {
        // TODO: dùng prev, current và next; thử thêm cách đệ quy.
        return null;
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
        Check.expect("reverse three nodes", solve(from(1, 2, 3)), from(3, 2, 1));
        Check.expect("empty list", solve(null), null);
        Check.summary();
    }
}
