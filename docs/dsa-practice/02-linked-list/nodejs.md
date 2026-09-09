# 2. Danh sách liên kết (Linked List) — Node.js (JavaScript)

## Lý thuyết

JS không có sẵn `LinkedList`, ta tự định nghĩa bằng `class`. Khái niệm giống Java: Singly/Doubly Linked List, dummy node, fast & slow pointers.

```js
class ListNode {
  constructor(val, next = null) {
    this.val = val;
    this.next = next;
  }
}

// Helper: tạo linked list từ array để test nhanh
function buildList(arr) {
  const dummy = new ListNode(0);
  let tail = dummy;
  for (const v of arr) {
    tail.next = new ListNode(v);
    tail = tail.next;
  }
  return dummy.next;
}

function listToArray(head) {
  const result = [];
  while (head) {
    result.push(head.val);
    head = head.next;
  }
  return result;
}
```

## Code mẫu — Duyệt và in danh sách

```js
function printList(head) {
  const parts = [];
  while (head) {
    parts.push(head.val);
    head = head.next;
  }
  console.log(parts.join(' -> ') + ' -> null');
}
```

## Bài tập

### Mức cơ bản

**Bài 2.1 — Tính độ dài danh sách liên kết**

<details><summary>Gợi ý</summary>Duyệt qua từng node, tăng biến đếm tới khi gặp `null`.</details>

---

**Bài 2.2 — Đảo ngược danh sách liên kết**

Input: `1 -> 2 -> 3 -> null` → Output: `3 -> 2 -> 1 -> null`

<details><summary>Gợi ý</summary>3 con trỏ `prev`, `curr`, `next`, giống Java.</details>

---

**Bài 2.3 — Tìm phần tử thứ k từ cuối danh sách**

Input: `1->2->3->4->5`, k=2 → Output: `4`

<details><summary>Gợi ý</summary>Cho `fast` đi trước `k` bước, sau đó `fast`/`slow` cùng đi đến khi `fast` hết danh sách.</details>

### Mức trung bình

**Bài 2.4 — Phát hiện chu trình (Floyd's Algorithm)**

<details><summary>Gợi ý</summary>`slow` đi 1 bước, `fast` đi 2 bước, kiểm tra `slow === fast`.</details>

---

**Bài 2.5 — Tìm điểm giữa danh sách**

Input: `1->2->3->4->5` → Output: node `3`

<details><summary>Gợi ý</summary>`slow` đi 1 bước, `fast` đi 2 bước, dừng khi `fast` chạm cuối.</details>

---

**Bài 2.6 — Merge 2 danh sách liên kết đã sắp xếp**

Input: `1->3->5`, `2->4->6` → Output: `1->2->3->4->5->6`

<details><summary>Gợi ý</summary>Dummy node + so sánh từng cặp node, nối node nhỏ hơn vào kết quả.</details>

### Mức nâng cao

**Bài 2.7 — Xóa node thứ N từ cuối danh sách trong 1 lần duyệt**

Input: `1->2->3->4->5`, n=2 → Output: `1->2->3->5`

<details><summary>Gợi ý</summary>Dummy node + fast/slow: cho `fast` đi trước n+1 bước.</details>

---

**Bài 2.8 — Kiểm tra danh sách liên kết có phải Palindrome**

Input: `1->2->2->1` → `true`

<details><summary>Gợi ý</summary>Tìm giữa, đảo nửa sau, so sánh — như Java. Có thể "ăn gian" trong JS bằng `listToArray` rồi so sánh với mảng đảo ngược, nhưng cách đó tốn O(n) bộ nhớ; nên luyện cách O(1) bộ nhớ trước.</details>

---

**Bài 2.9 — Gộp K danh sách liên kết đã sắp xếp**

Input: `[[1,4,5],[1,3,4],[2,6]]` → Output: `1->1->2->3->4->4->5->6`

<details><summary>Gợi ý</summary>JS không có PriorityQueue built-in. Cách đơn giản: chia để trị — merge từng cặp 2 danh sách (bài 2.6) lần lượt cho tới khi còn 1 danh sách. Nếu muốn dùng min-heap thật, phải tự cài đặt (xem chủ đề 8 — Heap).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự viết được Reverse Linked List
- [ ] Hiểu kỹ thuật fast/slow pointers
- [ ] So sánh được cách JS không có PriorityQueue built-in ảnh hưởng thế nào đến cách giải Merge K Lists so với Java
- [ ] Làm được Palindrome Linked List với O(1) bộ nhớ phụ
