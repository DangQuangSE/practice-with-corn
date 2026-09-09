# 3. Stack & Queue — Java

## Lý thuyết

- **Stack (LIFO)**: vào sau ra trước. Java dùng `Deque<T>` (khuyến nghị, thay cho class `Stack` cũ): `push()`, `pop()`, `peek()`.
- **Queue (FIFO)**: vào trước ra trước. Dùng `Deque<T>` hoặc `LinkedList<T>`: `offer()`/`add()`, `poll()`, `peek()`.
- **Deque (Double-ended queue)**: thêm/xóa cả 2 đầu O(1) — dùng cho monotonic stack/queue, sliding window.
- Ứng dụng Stack: kiểm tra ngoặc hợp lệ, undo/redo, đánh giá biểu thức, DFS.
- Ứng dụng Queue: BFS, xử lý theo thứ tự đến trước, task scheduling.

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); stack.push(2);
int top = stack.pop(); // 2

Deque<Integer> queue = new ArrayDeque<>();
queue.offer(1); queue.offer(2);
int front = queue.poll(); // 1
```

## Bài tập

### Mức cơ bản

**Bài 3.1 — Kiểm tra ngoặc hợp lệ (Valid Parentheses)**

Input: `"({[]})"` → `true`; `"(]"` → `false`

<details><summary>Gợi ý</summary>Gặp ngoặc mở thì push vào stack. Gặp ngoặc đóng thì pop và kiểm tra có khớp loại không.</details>

---

**Bài 3.2 — Triển khai Queue bằng 2 Stack**

<details><summary>Gợi ý</summary>`enqueue`: push vào stack1. `dequeue`: nếu stack2 rỗng, đổ hết stack1 sang stack2 (đảo thứ tự), rồi pop từ stack2.</details>

### Mức trung bình

**Bài 3.3 — Đánh giá biểu thức hậu tố (Evaluate Reverse Polish Notation)**

Input: `["2","1","+","3","*"]` → Output: `9` (tương đương `(2+1)*3`)

<details><summary>Gợi ý</summary>Gặp số thì push vào stack. Gặp toán tử thì pop 2 số, tính toán, push kết quả lại.</details>

---

**Bài 3.4 — Min Stack (Stack hỗ trợ lấy min trong O(1))**

<details><summary>Gợi ý</summary>Dùng 2 stack: 1 stack thường để lưu giá trị, 1 stack phụ lưu min hiện tại tại mỗi thời điểm push.</details>

---

**Bài 3.5 — Sliding Window Maximum**

Cho mảng và kích thước cửa sổ `k`, tìm max trong mỗi cửa sổ trượt.

Input: `nums=[1,3,-1,-3,5,3,6,7], k=3` → Output: `[3,3,5,5,6,7]`

<details><summary>Gợi ý</summary>Dùng **monotonic deque** lưu chỉ số, giữ deque luôn giảm dần giá trị. Loại bỏ chỉ số ra khỏi window khi cần. Mục tiêu độ phức tạp: O(n).</details>

### Mức nâng cao

**Bài 3.6 — Daily Temperatures (Monotonic Stack)**

Cho mảng nhiệt độ, với mỗi ngày tìm số ngày phải đợi để có ngày ấm hơn.

Input: `[73,74,75,71,69,72,76,73]` → Output: `[1,1,4,2,1,1,0,0]`

<details><summary>Gợi ý</summary>Dùng stack lưu chỉ số các ngày chưa tìm được ngày ấm hơn. Khi gặp ngày ấm hơn ngày ở đỉnh stack, pop và tính khoảng cách. Đây là kỹ thuật "next greater element" rất hay gặp.</details>

---

**Bài 3.7 — Largest Rectangle in Histogram**

Cho mảng độ cao các cột liền kề, tìm diện tích hình chữ nhật lớn nhất.

Input: `[2,1,5,6,2,3]` → Output: `10`

<details><summary>Gợi ý</summary>Dùng stack lưu chỉ số tăng dần độ cao. Khi gặp cột thấp hơn đỉnh stack, pop ra và tính diện tích với cột đó làm chiều cao, chiều rộng là khoảng giữa 2 chỉ số biên. Mục tiêu: O(n).</details>

---

**Bài 3.8 — Thiết kế Circular Queue**

<details><summary>Gợi ý</summary>Dùng mảng cố định kích thước k, 2 con trỏ `head` và `count` để biết vị trí thêm/xóa và biết queue đầy/rỗng.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu khi nào dùng Stack, khi nào dùng Queue
- [ ] Tự làm được Valid Parentheses và Min Stack
- [ ] Hiểu kỹ thuật monotonic stack (Daily Temperatures, Largest Rectangle)
- [ ] Hiểu kỹ thuật monotonic deque cho Sliding Window Maximum
