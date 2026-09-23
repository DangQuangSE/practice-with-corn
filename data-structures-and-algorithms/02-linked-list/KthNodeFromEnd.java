/**
 * Bài 2.3 — Tìm phần tử thứ k từ cuối danh sách
 * Difficulty: Easy
 */
public class KthNodeFromEnd {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static Integer solve(ListNode head, int k) {
        // TODO: cho fast đi trước k bước, rồi di chuyển fast và slow cùng nhau.
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
        Check.expect("second from end", solve(from(1, 2, 3, 4, 5), 2), 4);
        Check.expect("invalid k", solve(from(1, 2), 3), null);
        Check.summary();
    }
}
