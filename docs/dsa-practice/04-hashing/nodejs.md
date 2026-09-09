# 4. Hashing — Node.js (JavaScript)

## Lý thuyết

- **Map**: lưu key-value, key có thể là bất kỳ kiểu (object, number, string...), giữ thứ tự chèn, tra cứu O(1) trung bình.
- **Set**: tập hợp không trùng lặp, tra cứu O(1).
- Object thường (`{}`) cũng có thể dùng làm hashmap nhưng key luôn bị convert thành string, và có thể đụng các thuộc tính kế thừa từ `Object.prototype` — nên ưu tiên `Map`/`Set` cho code thuật toán.
- Không có TreeMap/TreeSet built-in trong JS — nếu cần thứ tự sắp xếp phải tự cài (BST/skip list) hoặc dùng array đã sort + binary search.

```js
const map = new Map();
map.set('a', 1);
map.get('b'); // undefined nếu không tồn tại — dùng ?? để default
map.has('a'); // true
map.set('a', (map.get('a') ?? 0) + 1); // tăng giá trị

const set = new Set([1, 2, 2, 3]); // {1, 2, 3}
```

## Bài tập

### Mức cơ bản

**Bài 4.1 — Đếm tần suất xuất hiện của các phần tử trong mảng**

Input: `[1,2,2,3,3,3]` → Output: `Map(1 => 1, 2 => 2, 3 => 3)`

<details><summary>Gợi ý</summary>Dùng `freq.set(n, (freq.get(n) ?? 0) + 1)`.</details>

---

**Bài 4.2 — Kiểm tra 2 chuỗi có là Anagram**

Input: `"listen"`, `"silent"` → `true`

<details><summary>Gợi ý</summary>Đếm tần suất ký tự chuỗi 1 vào Map, giảm dần khi duyệt chuỗi 2, kiểm tra không âm.</details>

---

**Bài 4.3 — Tìm phần tử xuất hiện duy nhất 1 lần**

Input: `[4,1,2,1,2]` → Output: `4`

<details><summary>Gợi ý</summary>Cách dễ: đếm tần suất bằng Map. Gợi ý nâng cao: dùng XOR (`nums.reduce((a, b) => a ^ b, 0)`) để giải O(1) bộ nhớ.</details>

### Mức trung bình

**Bài 4.4 — Nhóm các Anagram (Group Anagrams)**

Input: `["eat","tea","tan","ate","nat","bat"]`
Output: `[["eat","tea","ate"],["tan","nat"],["bat"]]`

<details><summary>Gợi ý</summary>Sắp xếp ký tự mỗi chuỗi (`[...s].sort().join('')`) làm khóa chuẩn hóa, nhóm vào Map<string, string[]>.</details>

---

**Bài 4.5 — Subarray Sum Equals K**

Input: `nums=[1,1,1], k=2` → Output: `2`

<details><summary>Gợi ý</summary>Prefix sum + Map. Nhớ khởi tạo `prefixCount.set(0, 1)` trước khi duyệt.</details>

---

**Bài 4.6 — Longest Consecutive Sequence**

Input: `[100,4,200,1,3,2]` → Output: `4`

<details><summary>Gợi ý</summary>Đưa vào Set, với mỗi số kiểm tra `n-1` có trong set không — nếu không, đó là điểm bắt đầu dãy, đếm tiếp `n+1, n+2,...`.</details>

### Mức nâng cao

**Bài 4.7 — Thiết kế cấu trúc LRU Cache**

<details><summary>Gợi ý</summary>`Map` trong JS giữ thứ tự chèn, và có thể "đưa lên cuối" bằng cách xóa rồi set lại key — lợi dụng đặc tính này để cài LRU mà không cần tự viết doubly linked list.</details>

---

**Bài 4.8 — Four Sum II**

<details><summary>Gợi ý</summary>Tính tổng cặp `A[i]+B[j]` lưu vào Map, rồi tra cứu `-(C[k]+D[l])`. Mục tiêu: O(n²) thay vì O(n⁴).</details>

---

**Bài 4.9 — Thiết kế HashMap từ đầu (không dùng Map/Object có sẵn)**

<details><summary>Gợi ý</summary>Dùng array of buckets, mỗi bucket là array chứa các cặp `[key, value]`. Hash function: `index = Math.abs(key) % capacity`.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu vì sao nên dùng `Map`/`Set` thay vì object thường khi làm bài thuật toán
- [ ] Tự làm được Group Anagrams và Subarray Sum Equals K
- [ ] Hiểu kỹ thuật prefix sum + hashmap
- [ ] Hiểu cách lợi dụng thứ tự chèn của `Map` trong JS để cài LRU Cache đơn giản hơn Java
