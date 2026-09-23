/**
 * Bài 2.6 — Gộp hai danh sách liên kết đã sắp xếp
 * Difficulty: Medium
 */
public class MergeTwoSortedLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        public String toString() {
            return val + (next == null ? "" : "->" + next);
        }
    }

    public static ListNode solve(ListNode first, ListNode second) {
        // TODO: dùng dummy node và luôn nối node nhỏ hơn.
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
        Check.expect("interleaved lists",
                solve(from(1, 3, 5), from(2, 4, 6)),
                from(1, 2, 3, 4, 5, 6));
        Check.summary();
    }
}
