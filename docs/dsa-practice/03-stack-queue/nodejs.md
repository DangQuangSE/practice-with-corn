# 3. Stack & Queue — Node.js (JavaScript)

## Lý thuyết

JS không có cấu trúc Stack/Queue/Deque built-in chuyên dụng — thường dùng `Array` cho Stack, và cẩn thận với Queue:

- **Stack (LIFO)**: dùng `Array.push()` / `Array.pop()` — cả 2 đều O(1).
- **Queue (FIFO)**: ⚠️ `Array.shift()` là O(n) vì phải dời toàn bộ phần tử còn lại. Nếu cần Queue hiệu năng cao, tự cài bằng linked list hoặc dùng kỹ thuật con trỏ trên array (xem bài 3.2/3.8).
- **Deque**: tự cài bằng doubly linked list, hoặc dùng array kèm con trỏ đầu/cuối nếu kích thước biết trước.

```js
// Stack
const stack = [];
stack.push(1); stack.push(2);
const top = stack.pop(); // 2

// Queue (chấp nhận O(n) cho bài nhỏ)
const queue = [];
queue.push(1); queue.push(2);
const front = queue.shift(); // 1, nhưng O(n)
```

## Bài tập

### Mức cơ bản

**Bài 3.1 — Kiểm tra ngoặc hợp lệ (Valid Parentheses)**

Input: `"({[]})"` → `true`; `"(]"` → `false`

<details><summary>Gợi ý</summary>Gặp ngoặc mở thì push vào stack, gặp ngoặc đóng thì pop và kiểm tra khớp loại.</details>

---

**Bài 3.2 — Triển khai Queue hiệu năng tốt bằng Array + con trỏ đầu**

<details><summary>Gợi ý</summary>Thay vì `shift()` (O(n)), dùng con trỏ `head` để "đánh dấu" phần tử đã dequeue, định kỳ dọn dẹp mảng khi `head` quá lớn để tránh leak bộ nhớ.</details>

### Mức trung bình

**Bài 3.3 — Đánh giá biểu thức hậu tố (Evaluate RPN)**

Input: `["2","1","+","3","*"]` → Output: `9`

<details><summary>Gợi ý</summary>Gặp số push vào stack, gặp toán tử pop 2 số và tính toán. Cẩn thận `Math.trunc()` khi chia để giống Java integer division.</details>

---

**Bài 3.4 — Min Stack**

<details><summary>Gợi ý</summary>2 stack: 1 lưu giá trị, 1 lưu min hiện tại tại mỗi thời điểm push.</details>

---

**Bài 3.5 — Sliding Window Maximum**

Input: `nums=[1,3,-1,-3,5,3,6,7], k=3` → Output: `[3,3,5,5,6,7]`

<details><summary>Gợi ý</summary>Monotonic deque lưu chỉ số, giảm dần giá trị. Có thể dùng array làm deque (push/pop cuối O(1)); shift đầu chấp nhận được vì tổng số lần shift bị chặn bởi n.</details>

### Mức nâng cao

**Bài 3.6 — Daily Temperatures (Monotonic Stack)**

Input: `[73,74,75,71,69,72,76,73]` → Output: `[1,1,4,2,1,1,0,0]`

<details><summary>Gợi ý</summary>Stack lưu chỉ số chưa có đáp án, pop khi gặp nhiệt độ ấm hơn và tính khoảng cách.</details>

---

**Bài 3.7 — Largest Rectangle in Histogram**

Input: `[2,1,5,6,2,3]` → Output: `10`

<details><summary>Gợi ý</summary>Stack lưu chỉ số tăng dần độ cao; khi gặp cột thấp hơn đỉnh stack, pop và tính diện tích.</details>

---

**Bài 3.8 — Thiết kế Circular Queue**

<details><summary>Gợi ý</summary>Array cố định kích thước k, con trỏ `head` và `count` để biết vị trí thêm/xóa và trạng thái đầy/rỗng.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu vì sao `Array.shift()` trong JS là O(n), khác với việc dùng Deque trong Java
- [ ] Tự làm được Valid Parentheses và Min Stack
- [ ] Hiểu kỹ thuật monotonic stack (Daily Temperatures, Largest Rectangle)
- [ ] Biết cách tự cài Queue hiệu năng tốt khi không có built-in
