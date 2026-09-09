# 11. Chủ đề nâng cao — Node.js (JavaScript)

Tổng hợp các kỹ thuật nâng cao thường gặp trong phỏng vấn: Trie, Union-Find (đầy đủ), Sliding Window pattern, Bit Manipulation.

## Lý thuyết

### Trie (Cây tiền tố / Prefix Tree)

```js
class TrieNode {
  constructor() {
    this.children = new Map();
    this.isEndOfWord = false;
  }
}
```

### Union-Find (Disjoint Set Union) đầy đủ

```js
class UnionFind {
  #parent;
  #rank;

  constructor(n) {
    this.#parent = Array.from({ length: n }, (_, i) => i);
    this.#rank = new Array(n).fill(0);
  }

  find(x) {
    if (this.#parent[x] !== x) {
      this.#parent[x] = this.find(this.#parent[x]); // path compression
    }
    return this.#parent[x];
  }

  union(x, y) {
    let rootX = this.find(x), rootY = this.find(y);
    if (rootX === rootY) return;
    if (this.#rank[rootX] < this.#rank[rootY]) [rootX, rootY] = [rootY, rootX];
    this.#parent[rootY] = rootX;
    if (this.#rank[rootX] === this.#rank[rootY]) this.#rank[rootX]++;
  }
}
```

### Bit Manipulation — các trick cần nhớ

| Trick | Ý nghĩa |
|---|---|
| `n & (n-1)` | Xóa bit 1 cuối cùng |
| `n & (-n)` | Lấy ra bit 1 cuối cùng |
| `n & 1` | Kiểm tra số chẵn/lẻ |
| `a ^ a = 0`, `a ^ 0 = a` | XOR dùng để tìm phần tử lẻ/đơn |
| `n << 1` / `n >> 1` | Nhân/chia 2 |
| `n.toString(2).split('1').length - 1` | Đếm số bit 1 (cách đơn giản, không tối ưu) |

⚠️ JS dùng toán tử bitwise (`&`, `|`, `^`, `<<`, `>>`, `>>>`) trên số 32-bit có dấu — số lớn hơn `2^31` sẽ bị tràn/sai kết quả khác với Java `int`. Cẩn thận khi áp dụng bit trick cho số lớn.

## Bài tập

### Mức cơ bản — Bit Manipulation

**Bài 11.1 — Đếm số bit 1 trong số nguyên (Hamming Weight)**

Input: `n=11` (1011 nhị phân) → Output: `3`

<details><summary>Gợi ý</summary>Lặp `while (n !== 0) { count += n & 1; n >>>= 1; }` (dùng `>>>` — unsigned right shift — để tránh vấn đề số âm), hoặc dùng trick `n & (n-1)`.</details>

---

**Bài 11.2 — Tìm số duy nhất (Single Number) bằng XOR**

Input: `[4,1,2,1,2]` → Output: `4`

<details><summary>Gợi ý</summary>`nums.reduce((a, b) => a ^ b, 0)` — các số xuất hiện 2 lần tự triệt tiêu.</details>

### Mức trung bình — Sliding Window pattern

**Bài 11.3 — Minimum Window Substring**

Tìm cửa sổ con nhỏ nhất trong `s` chứa tất cả ký tự của `t` (kể cả trùng lặp).

Input: `s="ADOBECODEBANC", t="ABC"` → Output: `"BANC"`

<details><summary>Gợi ý</summary>Sliding window biến đổi kích thước: mở rộng `right` để "thu thập đủ" ký tự cần, sau đó cố thu hẹp `left` nhiều nhất có thể mà vẫn còn đủ. Dùng `Map` đếm ký tự cần và biến đếm "đã đủ bao nhiêu loại ký tự".</details>

---

**Bài 11.4 — Permutation in String**

Kiểm tra `s2` có chứa hoán vị nào của `s1` là substring không.

<details><summary>Gợi ý</summary>Sliding window kích thước cố định = độ dài s1, dùng array đếm 26 ký tự, so sánh 2 array đếm có giống nhau không.</details>

### Mức nâng cao — Trie

**Bài 11.5 — Implement Trie (Prefix Tree)**

Thiết kế Trie hỗ trợ `insert(word)`, `search(word)`, `startsWith(prefix)`.

<details><summary>Gợi ý</summary>Mỗi node có `Map` children và cờ `isEndOfWord`. `insert`: đi qua từng ký tự, tạo node mới nếu chưa có. `search`: đi hết từ và kiểm tra `isEndOfWord`. `startsWith`: chỉ cần đi hết được.</details>

---

**Bài 11.6 — Word Search II**

Cho lưới ký tự 2D và danh sách từ, tìm tất cả từ xuất hiện trong lưới.

<details><summary>Gợi ý</summary>Xây Trie từ danh sách từ trước, DFS từ mỗi ô đồng thời "đi" trên Trie — tránh kiểm tra từng từ riêng biệt, dùng Trie để cắt nhánh sớm khi không còn từ nào khớp tiền tố.</details>

### Mức nâng cao — Union-Find / Advanced Graph

**Bài 11.7 — Number of Provinces**

Cho ma trận kề `isConnected[i][j]`, đếm số "tỉnh" (nhóm liên thông).

<details><summary>Gợi ý</summary>Union-Find: với mỗi cặp `(i,j)` mà `isConnected[i][j] === 1`, union i và j. Đếm số root khác nhau ở cuối.</details>

---

**Bài 11.8 — Accounts Merge**

Gộp các account có chung email thành 1 nhóm.

<details><summary>Gợi ý</summary>Union-Find trên index account, union 2 account nếu chung ít nhất 1 email. Dùng `Map` (email → index account đầu tiên có email đó).</details>

---

**Bài 11.9 — Design Add and Search Words Data Structure**

Thiết kế cấu trúc hỗ trợ `addWord(word)` và `search(word)` trong đó `search` cho phép ký tự `.` đại diện cho bất kỳ ký tự nào.

<details><summary>Gợi ý</summary>Dùng Trie. Khi search gặp `.`, phải thử đệ quy với TẤT CẢ children của node hiện tại (DFS trên Trie) thay vì chỉ 1 nhánh cố định.</details>

## Checklist hoàn thành lộ trình

- [ ] Tự cài được Trie từ đầu và giải Word Search II
- [ ] Tự cài được Union-Find đầy đủ (path compression + union by rank)
- [ ] Thành thạo pattern sliding window biến đổi kích thước (Minimum Window Substring)
- [ ] Nhớ được các bit trick cơ bản, hiểu rủi ro tràn số khi dùng bitwise operator trong JS

**Sau khi hoàn thành toàn bộ 11 chủ đề**: hãy thử luyện tập tổng hợp trên LeetCode theo độ khó Easy → Medium → Hard, ưu tiên các bài thuộc chủ đề bạn còn yếu nhất. Thử giải lại cùng 1 bài bằng cả Java và Node.js để khắc sâu sự khác biệt giữa 2 ngôn ngữ.
