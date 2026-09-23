/**
 * Bài 2.5 — Tìm node ở giữa danh sách
 * Difficulty: Medium
 */
public class MiddleOfLinkedList {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static Integer solve(ListNode head) {
        // TODO: dùng slow/fast; với độ dài chẵn chọn node ở giữa bên phải.
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
        Check.expect("odd length", solve(from(1, 2, 3, 4, 5)), 3);
        Check.expect("even length", solve(from(1, 2, 3, 4)), 3);
        Check.summary();
    }
}
