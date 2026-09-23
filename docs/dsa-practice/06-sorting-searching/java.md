# 6. Sắp xếp & Tìm kiếm (Sorting & Searching) — Java

## Lý thuyết

### Các thuật toán sắp xếp cần tự cài được

| Thuật toán | Trung bình | Xấu nhất | Bộ nhớ | Ổn định |
|---|---|---|---|---|
| Bubble Sort | O(n²) | O(n²) | O(1) | Có |
| Selection Sort | O(n²) | O(n²) | O(1) | Không |
| Insertion Sort | O(n²) | O(n²) | O(1) | Có |
| Merge Sort | O(n log n) | O(n log n) | O(n) | Có |
| Quick Sort | O(n log n) | O(n²) | O(log n) | Không |

- `Arrays.sort()` cho mảng kiểu nguyên thủy dùng Dual-Pivot Quicksort; cho mảng object dùng TimSort (ổn định).
- **Binary Search** yêu cầu dữ liệu đã sắp xếp, độ phức tạp O(log n).

## Code mẫu — Khung Merge Sort & Quick Sort

```java
static void mergeSort(int[] arr, int left, int right) {
    if (left >= right) return;
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);
    merge(arr, left, mid, right); // tự code: gộp 2 nửa đã sort thành 1 mảng sort
}

static void quickSort(int[] arr, int low, int high) {
    if (low >= high) return;
    int pivotIndex = partition(arr, low, high); // tự code: chọn pivot, hoán đổi
    quickSort(arr, low, pivotIndex - 1);
    quickSort(arr, pivotIndex + 1, high);
}
```

## Bài tập

### Mức cơ bản

**Bài 6.1 — Tự cài Binary Search**

Input: `nums=[1,3,5,7,9,11], target=7` → Output: `3` (chỉ số)

<details><summary>Gợi ý</summary>Dùng `left + (right - left) / 2` để tránh overflow so với `(left + right) / 2`. Mục tiêu: O(log n).</details>

---

**Bài 6.2 — Tự cài Insertion Sort**

<details><summary>Gợi ý</summary>Với mỗi phần tử từ vị trí 1, "chèn" nó vào đúng vị trí trong phần đã sắp xếp phía trước bằng cách dời các phần tử lớn hơn sang phải.</details>

---

**Bài 6.3 — Sắp xếp mảng object theo nhiều tiêu chí (Comparator)**

Sắp xếp danh sách `Person(name, age)` theo tuổi tăng dần, nếu trùng tuổi thì theo tên alphabet.

<details><summary>Gợi ý</summary>Dùng `Comparator.comparingInt(...).thenComparing(...)`.</details>

### Mức trung bình

**Bài 6.4 — Tìm kiếm trong mảng xoay (Search in Rotated Sorted Array)**

Input: `nums=[4,5,6,7,0,1,2], target=0` → Output: `4`

<details><summary>Gợi ý</summary>Vẫn dùng binary search, nhưng cần xác định nửa nào (trái/phải của mid) đang "sắp xếp đúng thứ tự" để quyết định thu hẹp về bên nào.</details>

---

**Bài 6.5 — Tìm vị trí chèn (Search Insert Position)**

Input: `nums=[1,3,5,6], target=2` → Output: `1`

<details><summary>Gợi ý</summary>Biến thể binary search: tìm vị trí đầu tiên mà `nums[i] >= target` (lower bound).</details>

---

**Bài 6.6 — Sắp xếp mảng gồm 0, 1, 2 (Dutch National Flag / Sort Colors)**

Input: `[2,0,2,1,1,0]` → Output: `[0,0,1,1,2,2]`, yêu cầu O(n) thời gian, O(1) bộ nhớ, 1 lần duyệt.

<details><summary>Gợi ý</summary>Dùng 3 con trỏ: `low` (biên 0), `mid` (con trỏ duyệt), `high` (biên 2). Hoán đổi tùy giá trị `arr[mid]`.</details>

### Mức nâng cao

**Bài 6.7 — Tìm K phần tử lớn nhất (Kth Largest Element)**

Input: `nums=[3,2,1,5,6,4], k=2` → Output: `5`

<details><summary>Gợi ý</summary>Cách đơn giản: sort rồi lấy phần tử thứ k từ cuối O(n log n). Cách tối ưu O(n) trung bình: **Quickselect** (biến thể quicksort chỉ đệ quy 1 nhánh, dựa trên `partition`).</details>

---

**Bài 6.8 — Tìm phần tử median của 2 mảng đã sắp xếp (Median of Two Sorted Arrays)**

Input: `nums1=[1,3], nums2=[2]` → Output: `2.0`

<details><summary>Gợi ý</summary>Bài khó kinh điển — dùng binary search trên vị trí "cắt" (partition) của 2 mảng sao cho nửa trái có đúng (m+n+1)/2 phần tử và mọi phần tử nửa trái ≤ mọi phần tử nửa phải. Yêu cầu độ phức tạp O(log(min(m,n))).</details>

---

**Bài 6.9 — Merge Intervals**

Input: `[[1,3],[2,6],[8,10],[15,18]]` → Output: `[[1,6],[8,10],[15,18]]`

<details><summary>Gợi ý</summary>Sort theo điểm bắt đầu, sau đó duyệt và gộp các interval chồng lấn (so sánh điểm kết thúc interval cuối trong kết quả với điểm bắt đầu interval hiện tại).</details>

---

**Bài 6.10 — Find First and Last Position of Element** (Medium)

Trong mảng đã sắp xếp, tìm vị trí đầu và cuối của target; nếu không có trả về [-1,-1].

Input: nums=[5,7,7,8,8,10], target=8 -> Output: [3,4]

<details><summary>Gợi ý</summary>Dùng hai binary search: một tìm lower bound, một tìm vị trí đầu tiên lớn hơn target rồi trừ 1.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự cài được Merge Sort và Quick Sort từ đầu, không nhìn code
- [ ] Hiểu binary search và các biến thể (lower bound, tìm trong mảng xoay)
- [ ] Làm được Quickselect cho bài Kth Largest Element
- [ ] Hiểu khi nào nên dùng sort + scan thay vì cấu trúc dữ liệu phức tạp hơn
