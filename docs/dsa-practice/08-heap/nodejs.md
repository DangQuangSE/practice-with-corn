# 8. Heap & Priority Queue — Node.js (JavaScript)

## Lý thuyết

⚠️ Khác biệt lớn nhất so với Java: **JS không có PriorityQueue/Heap built-in**. Phải tự cài bằng array + sift up/down (giống cách Java triển khai `PriorityQueue` nội bộ).

- Với index `i` (0-based): con trái = `2i+1`, con phải = `2i+2`, cha = `Math.floor((i-1)/2)`.
- Nên viết 1 class `Heap` tổng quát nhận `compare` function, dùng lại cho mọi bài.

## Bài tập 0 (bắt buộc làm trước) — Tự cài class Heap tổng quát

Viết 1 class `Heap` hỗ trợ `push(val)`, `pop()`, `peek()`, `size`, nhận vào 1 hàm `compare(a, b)` để quyết định thứ tự ưu tiên (giống `Comparator` trong Java). Dùng lại class này cho tất cả bài tập bên dưới.

<details><summary>Gợi ý</summary>

- `push`: thêm vào cuối array, rồi "sift up" — so sánh với cha, hoán đổi nếu cần, lặp lại tới gốc.
- `pop`: lưu lại phần tử gốc để trả về, đưa phần tử cuối array lên gốc, rồi "sift down" — so sánh với 2 con, hoán đổi với con "ưu tiên hơn" nếu cần, lặp lại tới khi đúng vị trí.
- Min-heap số: `compare = (a, b) => a - b`. Max-heap số: `compare = (a, b) => b - a`.
- Test class bằng cách push một loạt số ngẫu nhiên rồi pop liên tục, kiểm tra thứ tự ra có đúng không.
</details>

### Mức cơ bản

**Bài 8.1 — Tìm K phần tử nhỏ nhất trong mảng**

Input: `nums=[7,10,4,3,20,15], k=3` → Output: `[3,4,7]`

<details><summary>Gợi ý</summary>Dùng max-heap kích thước k (từ class `Heap` vừa cài): nếu phần tử mới nhỏ hơn đỉnh heap thì pop đỉnh, push phần tử mới. Mục tiêu: O(n log k).</details>

---

**Bài 8.2 — Kiểm tra mảng có phải Min-Heap hợp lệ**

<details><summary>Gợi ý</summary>Với mỗi node ở index i, kiểm tra `arr[i] <= arr[2i+1]` và `arr[i] <= arr[2i+2]` (nếu tồn tại).</details>

### Mức trung bình

**Bài 8.3 — Kth Largest Element in a Stream**

<details><summary>Gợi ý</summary>Dùng min-heap kích thước k — đỉnh heap luôn là phần tử lớn thứ k trong số đã thêm.</details>

---

**Bài 8.4 — Top K Frequent Elements**

Input: `nums=[1,1,1,2,2,3], k=2` → Output: `[1,2]`

<details><summary>Gợi ý</summary>Đếm tần suất bằng Map, dùng min-heap kích thước k so sánh theo tần suất (`compare = (a, b) => a[1] - b[1]` nếu lưu entry `[value, count]`).</details>

---

**Bài 8.5 — Merge K Sorted Lists bằng Heap**

<details><summary>Gợi ý</summary>Thay cách "pairwise merge" ở [02-linked-list/nodejs.md](../02-linked-list/nodejs.md) bài 2.9 bằng heap thật: push node đầu mỗi list vào min-heap (so sánh theo `val`), mỗi lần pop ra node nhỏ nhất thì push tiếp `node.next` của nó nếu còn.</details>

### Mức nâng cao

**Bài 8.6 — Find Median from Data Stream**

<details><summary>Gợi ý</summary>2 heap: max-heap chứa nửa nhỏ, min-heap chứa nửa lớn, luôn giữ cân bằng kích thước (chênh lệch tối đa 1).</details>

---

**Bài 8.7 — Task Scheduler**

Input: `tasks=['A','A','A','B','B','B'], n=2` → Output: `8`

<details><summary>Gợi ý</summary>Đếm tần suất, max-heap ưu tiên task tần suất cao nhất, kèm 1 queue "cooldown" lưu `[remainingCount, availableTime]` để biết khi nào task được quay lại heap.</details>

---

**Bài 8.8 — K Closest Points to Origin**

Input: `points=[[1,3],[-2,2]], k=1` → Output: `[[-2,2]]`

<details><summary>Gợi ý</summary>Max-heap kích thước k, so sánh theo khoảng cách bình phương `x*x + y*y` (tránh tính sqrt không cần thiết).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự cài được class Heap tổng quát (nhận comparator) từ đầu
- [ ] Hiểu pattern "heap kích thước k" để giải bài Top-K
- [ ] Làm được Find Median from Data Stream (2 heap)
- [ ] Ghi nhớ: JS không có PriorityQueue built-in — luôn cần class Heap riêng khi đi phỏng vấn/thi
