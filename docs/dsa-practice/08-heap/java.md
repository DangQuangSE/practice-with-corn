# 8. Heap & Priority Queue — Java

## Lý thuyết

- **Heap** là cây nhị phân gần hoàn chỉnh (complete binary tree), thường lưu bằng array.
  - **Min-Heap**: node gốc luôn là phần tử nhỏ nhất.
  - **Max-Heap**: node gốc luôn là phần tử lớn nhất.
- Với index `i` (0-based): con trái = `2i+1`, con phải = `2i+2`, cha = `(i-1)/2`.
- Thao tác `insert`/`extractMin` đều O(log n) nhờ "sift up"/"sift down".
- Java có sẵn `PriorityQueue<T>` — **mặc định là min-heap**. Muốn max-heap: truyền `Comparator.reverseOrder()` hoặc `(a,b) -> b - a`.

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
minHeap.offer(5); minHeap.offer(1); minHeap.offer(3);
minHeap.poll(); // 1 (nhỏ nhất)
```

## Code mẫu — Khung tự cài Min-Heap

```java
class MinHeap {
    private List<Integer> heap = new ArrayList<>();

    void insert(int val) {
        heap.add(val);
        siftUp(heap.size() - 1); // tự code: đẩy phần tử mới lên đúng vị trí
    }

    int extractMin() {
        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0); // tự code: đẩy phần tử xuống đúng vị trí
        }
        return min;
    }
}
```

## Bài tập

Khuyến khích tự cài `MinHeap`/`MaxHeap` từ đầu (dựa trên khung ở trên) trước khi dùng `PriorityQueue` có sẵn để giải các bài dưới đây.

### Mức cơ bản

**Bài 8.1 — Tìm K phần tử nhỏ nhất trong mảng**

Input: `nums=[7,10,4,3,20,15], k=3` → Output: `[3,4,7]`

<details><summary>Gợi ý</summary>Dùng max-heap kích thước k: nếu phần tử mới nhỏ hơn đỉnh heap thì pop đỉnh, push phần tử mới — heap luôn giữ k phần tử nhỏ nhất. Mục tiêu: O(n log k).</details>

---

**Bài 8.2 — Kiểm tra mảng có phải Min-Heap hợp lệ**

<details><summary>Gợi ý</summary>Với mỗi node ở index i, kiểm tra `arr[i] <= arr[2i+1]` và `arr[i] <= arr[2i+2]` (nếu tồn tại).</details>

### Mức trung bình

**Bài 8.3 — Kth Largest Element in a Stream**

Thiết kế class luôn trả về phần tử lớn thứ k khi có số mới được thêm vào.

<details><summary>Gợi ý</summary>Dùng min-heap kích thước k — đỉnh heap luôn là phần tử lớn thứ k trong số đã thêm. Mục tiêu: O(log k) mỗi lần thêm.</details>

---

**Bài 8.4 — Top K Frequent Elements**

Input: `nums=[1,1,1,2,2,3], k=2` → Output: `[1,2]`

<details><summary>Gợi ý</summary>Đếm tần suất bằng HashMap, rồi dùng min-heap kích thước k (so sánh theo tần suất) để giữ k phần tử có tần suất cao nhất.</details>

---

**Bài 8.5 — Merge K Sorted Lists bằng Priority Queue** *(đã làm ở chủ đề 2, ôn lại tư duy heap)*

<details><summary>Gợi ý</summary>Xem [02-linked-list/java.md](../02-linked-list/java.md) bài 2.9 — minh họa rõ việc dùng heap để luôn lấy phần tử nhỏ nhất trong K nguồn.</details>

### Mức nâng cao

**Bài 8.6 — Find Median from Data Stream**

Thiết kế class hỗ trợ thêm số liên tục và trả về median (trung vị) tại bất kỳ thời điểm.

<details><summary>Gợi ý</summary>Dùng 2 heap: max-heap chứa nửa nhỏ, min-heap chứa nửa lớn. Luôn giữ cân bằng kích thước giữa 2 heap (chênh lệch tối đa 1).</details>

---

**Bài 8.7 — Task Scheduler**

Cho danh sách task (ký tự) và thời gian nghỉ `n` giữa 2 lần thực hiện task giống nhau, tìm tổng thời gian tối thiểu để hoàn thành tất cả.

Input: `tasks=['A','A','A','B','B','B'], n=2` → Output: `8` (A B idle A B idle A B)

<details><summary>Gợi ý</summary>Đếm tần suất, dùng max-heap để luôn ưu tiên thực hiện task có tần suất cao nhất, kèm hàng đợi "cooldown" để biết khi nào task được phép quay lại heap.</details>

---

**Bài 8.8 — K Closest Points to Origin**

Tìm K điểm gần gốc tọa độ (0,0) nhất.

Input: `points=[[1,3],[-2,2]], k=1` → Output: `[[-2,2]]`

<details><summary>Gợi ý</summary>Dùng max-heap kích thước k theo khoảng cách bình phương (tránh tính sqrt không cần thiết).</details>

---

**Bài 8.9 — Reorganize String** (Medium)

Sắp xếp lại ký tự sao cho hai ký tự kề nhau không giống nhau; nếu không thể, trả về chuỗi rỗng.

Input: aab -> Output: aba

<details><summary>Gợi ý</summary>Dùng max-heap theo tần suất, mỗi lần lấy hai ký tự có tần suất cao nhất rồi đưa lại heap sau khi dùng.</details>

---

**Bài 8.10 — IPO / Maximize Capital** (Medium)

Chọn tối đa k dự án để tối đa hóa vốn; một dự án chỉ được chọn khi vốn hiện tại đủ lớn.

Input: k=2, w=0, profits=[1,2,3], capital=[0,1,1] -> Output: 4

<details><summary>Gợi ý</summary>Sắp xếp dự án theo capital yêu cầu, đưa các dự án đủ điều kiện vào max-heap theo profit rồi chọn profit lớn nhất.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự cài được Min-Heap/Max-Heap từ đầu (sift up/sift down)
- [ ] Hiểu pattern "heap kích thước k" để giải bài Top-K
- [ ] Làm được Find Median from Data Stream (2 heap)
- [ ] Hiểu khi nào nên dùng PriorityQueue thay vì sort toàn bộ mảng
