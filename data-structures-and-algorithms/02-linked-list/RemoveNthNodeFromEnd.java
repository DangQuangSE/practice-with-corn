/**
 * Bài 2.7 — Xóa node thứ n từ cuối danh sách
 * Difficulty: Medium
 */
public class RemoveNthNodeFromEnd {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        public String toString() {
            return val + (next == null ? "" : "->" + next);
        }
    }

    public static ListNode solve(ListNode head, int n) {
        // TODO: dummy + fast đi trước n+1 bước, rồi xóa slow.next.
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
        Check.expect("remove middle", solve(from(1, 2, 3, 4, 5), 2), from(1, 2, 3, 5));
        Check.expect("remove head", solve(from(1, 2), 2), from(2));
        Check.summary();
    }
}
