# 4. Hashing — Java

## Lý thuyết

- **HashMap<K,V>**: lưu cặp key-value, tra cứu trung bình O(1) nhờ hàm hash. Không đảm bảo thứ tự.
- **HashSet<T>**: tập hợp không trùng lặp, tra cứu O(1).
- **LinkedHashMap/LinkedHashSet**: giữ thứ tự chèn.
- **TreeMap/TreeSet**: giữ thứ tự sắp xếp, các thao tác O(log n) (dựa trên Red-Black Tree).
- Hash collision (va đập) xảy ra khi 2 key khác nhau có cùng hash code → ảnh hưởng worst-case O(n). Java dùng chuỗi liên kết hoặc cây đỏ-đen (từ Java 8) để xử lý collision.
- Khi dùng object tự định nghĩa làm key, phải override đúng `equals()` và `hashCode()`.

```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.getOrDefault("b", 0); // 0 nếu không tồn tại
map.computeIfAbsent("c", k -> 0);
map.merge("a", 1, Integer::sum); // tăng giá trị "a" lên 1
```

## Bài tập

### Mức cơ bản

**Bài 4.1 — Đếm tần suất xuất hiện của các phần tử trong mảng**

Input: `[1,2,2,3,3,3]` → Output: `{1=1, 2=2, 3=3}`

<details><summary>Gợi ý</summary>Dùng `map.merge(key, 1, Integer::sum)` để tăng giá trị, hoặc `getOrDefault` + `put`.</details>

---

**Bài 4.2 — Kiểm tra 2 chuỗi có là Anagram (đảo ký tự của nhau)**

Input: `"listen"`, `"silent"` → `true`

<details><summary>Gợi ý</summary>Đếm tần suất ký tự của chuỗi 1, sau đó giảm dần khi duyệt chuỗi 2. Nếu khớp hoàn toàn → anagram.</details>

---

**Bài 4.3 — Tìm phần tử xuất hiện duy nhất 1 lần (các phần tử khác đều xuất hiện 2 lần)**

Input: `[4,1,2,1,2]` → Output: `4`

<details><summary>Gợi ý</summary>Cách dễ: đếm tần suất bằng HashMap. Gợi ý nâng cao: giải O(1) bộ nhớ bằng XOR (`a ^ a = 0`) thay vì HashMap — xem chủ đề 11 (Bit Manipulation).</details>

### Mức trung bình

**Bài 4.4 — Nhóm các Anagram (Group Anagrams)**

Input: `["eat","tea","tan","ate","nat","bat"]`
Output: `[["eat","tea","ate"],["tan","nat"],["bat"]]`

<details><summary>Gợi ý</summary>Sắp xếp ký tự trong mỗi chuỗi để tạo "khóa chuẩn hóa" (canonical key) — các anagram sẽ có cùng khóa này. Dùng HashMap<String, List<String>> để nhóm.</details>

---

**Bài 4.5 — Subarray Sum Equals K**

Cho mảng và số K, đếm số dãy con liên tiếp có tổng bằng K.

Input: `nums=[1,1,1], k=2` → Output: `2`

<details><summary>Gợi ý</summary>Dùng **prefix sum** + HashMap. Nếu `prefixSum[j] - prefixSum[i] = k` thì dãy con (i, j] có tổng k. Lưu số lần xuất hiện của mỗi prefix sum, nhớ khởi tạo `prefixCount.put(0, 1)`.</details>

---

**Bài 4.6 — Longest Consecutive Sequence**

Tìm độ dài dãy số nguyên liên tiếp dài nhất (không cần liền kề trong mảng gốc).

Input: `[100,4,200,1,3,2]` → Output: `4` (dãy `1,2,3,4`)

<details><summary>Gợi ý</summary>Đưa tất cả vào HashSet. Với mỗi số `n`, nếu `n-1` không có trong set thì `n` là điểm bắt đầu dãy — đếm tiếp `n+1, n+2,...` đến khi không còn trong set. Mục tiêu: O(n).</details>

### Mức nâng cao

**Bài 4.7 — Thiết kế cấu trúc LRU Cache**

Thiết kế cache với capacity cố định, khi đầy thì loại bỏ phần tử ít dùng gần đây nhất (Least Recently Used).

<details><summary>Gợi ý</summary>Kết hợp `LinkedHashMap` (đã hỗ trợ sẵn truy cập theo thứ tự, dùng constructor `accessOrder=true` và override `removeEldestEntry`) hoặc tự cài bằng HashMap + Doubly Linked List để có O(1) cho cả get/put.</details>

---

**Bài 4.8 — Four Sum II**

Cho 4 mảng A, B, C, D, đếm số tuple `(i,j,k,l)` sao cho `A[i]+B[j]+C[k]+D[l] = 0`.

<details><summary>Gợi ý</summary>Brute-force O(n⁴). Tối ưu: tính tất cả tổng cặp `A[i]+B[j]` lưu vào HashMap (tổng → số lần), rồi với mỗi cặp `C[k]+D[l]`, tra cứu `-( C[k]+D[l] )` trong map. Mục tiêu: O(n²).</details>

---

**Bài 4.9 — Thiết kế HashMap từ đầu (không dùng HashMap có sẵn)**

<details><summary>Gợi ý</summary>Dùng array of buckets (mỗi bucket là linked list các cặp key-value). Hash function: `index = hash(key) % capacity`. Xử lý collision bằng chuỗi liên kết (chaining).</details>

---

**Bài 4.10 — Contains Duplicate II** (Medium)

Kiểm tra có hai chỉ số i, j sao cho nums[i] == nums[j] và j - i <= k.

Input: nums=[1,2,3,1], k=3 -> Output: true

<details><summary>Gợi ý</summary>Lưu vị trí gần nhất của mỗi giá trị trong HashMap. Khi gặp lại, kiểm tra khoảng cách trước khi cập nhật vị trí mới.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu cách HashMap xử lý collision (chaining)
- [ ] Tự làm được Group Anagrams và Subarray Sum Equals K
- [ ] Hiểu kỹ thuật prefix sum + hashmap
- [ ] Hiểu nguyên lý LRU Cache (HashMap + Doubly Linked List)
