# 6. Sắp xếp & Tìm kiếm (Sorting & Searching) — Node.js (JavaScript)

## Lý thuyết

- `Array.prototype.sort()` — từ ES2019, tất cả engine JS (kể cả V8) đảm bảo thuật toán **ổn định** (stable sort), thường là TimSort hoặc biến thể.
- ⚠️ Lưu ý quan trọng: `sort()` mặc định so sánh theo **chuỗi (string)** — `[10, 2, 1].sort()` cho ra `[1, 10, 2]` (sai nếu muốn sort số). Luôn truyền comparator: `arr.sort((a, b) => a - b)`.
- Không có binary search built-in trong JS thuần (không như `Arrays.binarySearch` của Java) — phải tự viết.

```js
const nums = [10, 2, 33, 4];
nums.sort((a, b) => a - b); // [2, 4, 10, 33]
nums.sort((a, b) => b - a); // giảm dần
```

## Code mẫu — Khung Merge Sort & Quick Sort

```js
function mergeSort(arr, left = 0, right = arr.length - 1) {
  if (left >= right) return;
  const mid = Math.floor((left + right) / 2);
  mergeSort(arr, left, mid);
  mergeSort(arr, mid + 1, right);
  merge(arr, left, mid, right); // tự code: gộp 2 nửa đã sort thành 1 mảng sort
}

function quickSort(arr, low = 0, high = arr.length - 1) {
  if (low >= high) return;
  const pivotIndex = partition(arr, low, high); // tự code: chọn pivot, hoán đổi
  quickSort(arr, low, pivotIndex - 1);
  quickSort(arr, pivotIndex + 1, high);
}
```

## Bài tập

### Mức cơ bản

**Bài 6.1 — Tự cài Binary Search**

Input: `nums=[1,3,5,7,9,11], target=7` → Output: `3`

<details><summary>Gợi ý</summary>`mid = left + Math.floor((right - left) / 2)`. Mục tiêu: O(log n).</details>

---

**Bài 6.2 — Tự cài Insertion Sort**

<details><summary>Gợi ý</summary>Với mỗi phần tử từ vị trí 1, "chèn" vào đúng vị trí trong phần đã sắp xếp phía trước.</details>

---

**Bài 6.3 — Sắp xếp mảng object theo nhiều tiêu chí**

Sắp xếp danh sách `{ name, age }` theo tuổi tăng dần, nếu trùng tuổi thì theo tên alphabet.

<details><summary>Gợi ý</summary>Comparator trả về `a.age - b.age` nếu khác tuổi, ngược lại `a.name.localeCompare(b.name)`.</details>

### Mức trung bình

**Bài 6.4 — Tìm kiếm trong mảng xoay (Search in Rotated Sorted Array)**

Input: `nums=[4,5,6,7,0,1,2], target=0` → Output: `4`

<details><summary>Gợi ý</summary>Vẫn binary search, nhưng xác định nửa nào (trái/phải của mid) đang sắp xếp đúng thứ tự để quyết định thu hẹp về bên nào.</details>

---

**Bài 6.5 — Tìm vị trí chèn (Search Insert Position)**

Input: `nums=[1,3,5,6], target=2` → Output: `1`

<details><summary>Gợi ý</summary>Biến thể binary search: tìm vị trí đầu tiên mà `nums[i] >= target`.</details>

---

**Bài 6.6 — Sắp xếp mảng gồm 0, 1, 2 (Sort Colors)**

Input: `[2,0,2,1,1,0]` → Output: `[0,0,1,1,2,2]`

<details><summary>Gợi ý</summary>3 con trỏ `low`, `mid`, `high`; hoán đổi tùy giá trị `arr[mid]`.</details>

### Mức nâng cao

**Bài 6.7 — Tìm K phần tử lớn nhất (Kth Largest Element)**

Input: `nums=[3,2,1,5,6,4], k=2` → Output: `5`

<details><summary>Gợi ý</summary>Cách "ăn gian" trong JS: `nums.sort((a,b) => b-a)[k-1]` — O(n log n), đơn giản nhưng nên luyện Quickselect O(n) trung bình để hiểu sâu hơn (dựa trên `partition` ở phần code mẫu).</details>

---

**Bài 6.8 — Tìm phần tử median của 2 mảng đã sắp xếp**

Input: `nums1=[1,3], nums2=[2]` → Output: `2.0`

<details><summary>Gợi ý</summary>Binary search trên vị trí "cắt" của mảng ngắn hơn, đảm bảo nửa trái có đúng nửa số phần tử và mọi giá trị nửa trái ≤ nửa phải. Mục tiêu: O(log(min(m,n))).</details>

---

**Bài 6.9 — Merge Intervals**

Input: `[[1,3],[2,6],[8,10],[15,18]]` → Output: `[[1,6],[8,10],[15,18]]`

<details><summary>Gợi ý</summary>Sort theo điểm bắt đầu, duyệt và gộp interval chồng lấn bằng cách so sánh với interval cuối trong kết quả.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Luôn nhớ truyền comparator cho `Array.sort()` khi sort số
- [ ] Tự cài được Merge Sort và Quick Sort từ đầu
- [ ] Hiểu binary search và các biến thể
- [ ] Làm được Quickselect cho bài Kth Largest Element
