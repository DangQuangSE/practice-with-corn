/**
 * Bài 2.9 — Gộp K danh sách liên kết đã sắp xếp
 * Difficulty: Medium
 */
import java.util.PriorityQueue;

public class MergeKSortedLists {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static ListNode solve(ListNode[] lists) {
        // TODO: dùng min-heap chứa node đầu của mỗi danh sách.
        return null;
    }

    public static void main(String[] args) {
        Check.expect("empty input", solve(new ListNode[0]), null);
        Check.summary();
    }
}
