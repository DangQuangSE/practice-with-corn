# 2. Danh sách liên kết (Linked List) — Java

## Lý thuyết

- **Singly Linked List**: mỗi node trỏ tới node kế tiếp. Chèn/xóa ở đầu O(1), nhưng truy cập theo chỉ số O(n).
- **Doubly Linked List**: mỗi node có thêm con trỏ `prev` → có thể duyệt 2 chiều, xóa node khi đã có tham chiếu là O(1).
- Linked List phù hợp khi cần chèn/xóa nhiều ở đầu/giữa, không cần truy cập ngẫu nhiên.
- Kỹ thuật quan trọng: **dummy node** (node giả ở đầu) giúp xử lý edge case (xóa head) gọn hơn; **fast & slow pointers** để tìm giữa danh sách hoặc phát hiện chu trình (cycle).

### Định nghĩa node cơ bản

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}
```

## Code mẫu — Duyệt và in danh sách

```java
public class LinkedListDemo {
    static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val).append(" -> ");
            head = head.next;
        }
        sb.append("null");
        System.out.println(sb);
    }
}
```

## Bài tập

### Mức cơ bản

**Bài 2.1 — Tính độ dài danh sách liên kết**

<details><summary>Gợi ý</summary>Duyệt qua từng node, tăng biến đếm tới khi gặp `null`.</details>

---

**Bài 2.2 — Đảo ngược danh sách liên kết (Reverse Linked List)**

Input: `1 -> 2 -> 3 -> null` → Output: `3 -> 2 -> 1 -> null`

<details><summary>Gợi ý</summary>Dùng 3 con trỏ: `prev`, `curr`, `next`. Mỗi bước đổi hướng `curr.next` về `prev`. Cũng nên thử viết cách đệ quy để so sánh độ phức tạp bộ nhớ.</details>

---

**Bài 2.3 — Tìm phần tử thứ k từ cuối danh sách**

Input: `1->2->3->4->5`, k=2 → Output: `4`

<details><summary>Gợi ý</summary>Dùng 2 con trỏ: cho `fast` đi trước `k` bước, sau đó `fast` và `slow` cùng đi đến khi `fast` hết danh sách.</details>

### Mức trung bình

**Bài 2.4 — Phát hiện chu trình (Detect Cycle) — Floyd's Algorithm**

<details><summary>Gợi ý</summary>Con rùa và thỏ (tortoise & hare): `slow` đi 1 bước, `fast` đi 2 bước. Nếu có chu trình, chúng sẽ gặp nhau.</details>

---

**Bài 2.5 — Tìm điểm giữa danh sách (Middle of Linked List)**

Input: `1->2->3->4->5` → Output: node `3`

<details><summary>Gợi ý</summary>`slow` đi 1 bước, `fast` đi 2 bước. Khi `fast` chạm cuối, `slow` đang ở giữa.</details>

---

**Bài 2.6 — Merge 2 danh sách liên kết đã sắp xếp**

Input: `1->3->5`, `2->4->6` → Output: `1->2->3->4->5->6`

<details><summary>Gợi ý</summary>Dùng dummy node làm điểm bắt đầu, so sánh từng cặp node và nối node nhỏ hơn vào kết quả.</details>

### Mức nâng cao

**Bài 2.7 — Xóa node thứ N từ cuối danh sách trong 1 lần duyệt**

Input: `1->2->3->4->5`, n=2 → Output: `1->2->3->5`

<details><summary>Gợi ý</summary>Dùng dummy node + kỹ thuật fast/slow giống bài 2.3, nhưng cho `fast` đi trước n+1 bước để `slow` dừng đúng ngay trước node cần xóa.</details>

---

**Bài 2.8 — Kiểm tra danh sách liên kết có phải Palindrome**

Input: `1->2->2->1` → `true`

<details><summary>Gợi ý</summary>Tìm điểm giữa (bài 2.5), đảo ngược nửa sau (bài 2.2), rồi so sánh nửa đầu với nửa sau đã đảo. Tối ưu O(1) bộ nhớ phụ (không dùng mảng/stack).</details>

---

**Bài 2.9 — Gộp K danh sách liên kết đã sắp xếp (Merge K Sorted Lists)**

Input: `[[1,4,5],[1,3,4],[2,6]]` → Output: `1->1->2->3->4->4->5->6`

<details><summary>Gợi ý</summary>Dùng `PriorityQueue<ListNode>` (min-heap) để luôn lấy node nhỏ nhất trong K danh sách, hoặc chia để trị (merge từng cặp 2 danh sách theo bài 2.6). Độ phức tạp mục tiêu: O(N log k).</details>

---

**Bài 2.10 — Tìm giao điểm của hai danh sách liên kết (Intersection of Two Linked Lists)** (Medium)

Tìm node đầu tiên mà hai danh sách cùng trỏ tới; nếu không có giao điểm, trả về null.

Input: A = 3 -> 7 -> 8 -> 10, B = 99 -> 8 -> 10 -> Output: node 8

<details><summary>Gợi ý</summary>Dùng hai con trỏ. Khi một con trỏ đi hết danh sách, chuyển nó sang head của danh sách còn lại. Hai con trỏ sẽ đi cùng tổng số bước.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự viết được Reverse Linked List bằng cả vòng lặp và đệ quy
- [ ] Hiểu kỹ thuật fast/slow pointers (Floyd's cycle detection)
- [ ] Hiểu vì sao dummy node giúp code gọn hơn khi xóa/chèn ở đầu
- [ ] Làm được Merge K Sorted Lists với PriorityQueue
