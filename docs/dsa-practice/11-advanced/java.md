# 11. Chủ đề nâng cao — Java

Tổng hợp các kỹ thuật nâng cao thường gặp trong phỏng vấn: Trie, Union-Find (đầy đủ), Sliding Window pattern, Bit Manipulation.

## Lý thuyết

### Trie (Cây tiền tố / Prefix Tree)

Cấu trúc cây dùng để lưu tập từ, mỗi node đại diện 1 ký tự, hỗ trợ tìm kiếm theo tiền tố hiệu quả O(L) với L là độ dài từ.

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
}
```

### Union-Find (Disjoint Set Union) đầy đủ

```java
class UnionFind {
    int[] parent, rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return;
        if (rank[rootX] < rank[rootY]) { int t = rootX; rootX = rootY; rootY = t; }
        parent[rootY] = rootX;
        if (rank[rootX] == rank[rootY]) rank[rootX]++;
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
| `Integer.bitCount(n)` | Đếm số bit 1 |

## Bài tập

### Mức cơ bản — Bit Manipulation

**Bài 11.1 — Đếm số bit 1 trong số nguyên (Hamming Weight)**

Input: `n=11` (1011 nhị phân) → Output: `3`

<details><summary>Gợi ý</summary>Lặp `while (n != 0) { count += n & 1; n >>>= 1; }`, hoặc dùng trick `n & (n-1)` để xóa dần bit 1.</details>

---

**Bài 11.2 — Tìm số duy nhất (Single Number) bằng XOR**

Input: `[4,1,2,1,2]` → Output: `4`

<details><summary>Gợi ý</summary>XOR tất cả phần tử — các số xuất hiện 2 lần sẽ tự triệt tiêu (`a ^ a = 0`), chỉ còn lại số đơn.</details>

### Mức trung bình — Sliding Window pattern

**Bài 11.3 — Minimum Window Substring**

Tìm cửa sổ con nhỏ nhất trong `s` chứa tất cả ký tự của `t` (kể cả trùng lặp).

Input: `s="ADOBECODEBANC", t="ABC"` → Output: `"BANC"`

<details><summary>Gợi ý</summary>Sliding window biến đổi kích thước: mở rộng `right` để "thu thập đủ" ký tự cần, sau đó cố thu hẹp `left` nhiều nhất có thể mà vẫn còn đủ. Dùng HashMap đếm ký tự cần và biến đếm "đã đủ bao nhiêu loại ký tự".</details>

---

**Bài 11.4 — Permutation in String**

Kiểm tra `s2` có chứa hoán vị nào của `s1` là substring không.

<details><summary>Gợi ý</summary>Sliding window kích thước cố định = độ dài s1, dùng mảng đếm 26 ký tự, so sánh 2 mảng đếm (của window hiện tại và của s1) có giống nhau không.</details>

### Mức nâng cao — Trie

**Bài 11.5 — Implement Trie (Prefix Tree)**

Thiết kế Trie hỗ trợ `insert(word)`, `search(word)`, `startsWith(prefix)`.

<details><summary>Gợi ý</summary>Mỗi node có `Map<Character, TrieNode>` children và cờ `isEndOfWord`. `insert`: đi qua từng ký tự, tạo node mới nếu chưa có. `search`: đi hết từ và kiểm tra `isEndOfWord`. `startsWith`: chỉ cần đi hết được, không cần kiểm tra `isEndOfWord`.</details>

---

**Bài 11.6 — Word Search II**

Cho lưới ký tự 2D và danh sách từ, tìm tất cả từ xuất hiện trong lưới (có thể đi 4 hướng, không lặp ô).

<details><summary>Gợi ý</summary>Xây Trie từ danh sách từ trước, sau đó DFS từ mỗi ô trong lưới, đồng thời "đi" trên Trie — cách này tránh phải kiểm tra từng từ riêng biệt O(words × cells), thay vào đó duyệt 1 lần và dùng Trie để cắt nhánh sớm khi không còn từ nào khớp tiền tố.</details>

### Mức nâng cao — Union-Find / Advanced Graph

**Bài 11.7 — Number of Provinces**

Cho ma trận kề `isConnected[i][j]` biểu diễn các thành phố liên thông trực tiếp, đếm số "tỉnh" (nhóm liên thông).

<details><summary>Gợi ý</summary>Dùng Union-Find: với mỗi cặp `(i,j)` mà `isConnected[i][j] == 1`, union i và j. Đếm số root khác nhau ở cuối (số phần tử mà `find(i) == i`).</details>

---

**Bài 11.8 — Accounts Merge**

Gộp các account có chung email thành 1 nhóm.

<details><summary>Gợi ý</summary>Union-Find trên index account, union 2 account nếu chúng có chung ít nhất 1 email. Dùng HashMap (email → index account đầu tiên có email đó) để biết union account nào với account nào.</details>

---

**Bài 11.9 — Design Add and Search Words Data Structure**

Thiết kế cấu trúc hỗ trợ `addWord(word)` và `search(word)` trong đó `search` cho phép ký tự `.` đại diện cho bất kỳ ký tự nào.

<details><summary>Gợi ý</summary>Dùng Trie. Khi search gặp `.`, phải thử đệ quy với TẤT CẢ children của node hiện tại (DFS trên Trie) thay vì chỉ 1 nhánh cố định.</details>

---

**Bài 11.10 — Maximum XOR for Each Query** (Medium)

Với mỗi prefix XOR của mảng, chọn số x trong [0, 2^maximumBit - 1] để XOR đạt lớn nhất.

Input: nums=[0,1,1,3], maximumBit=2 -> Output: [0,3,2,3]

<details><summary>Gợi ý</summary>Tổng XOR của toàn prefix và mask toàn bit 1 cho biết ngay giá trị x tối ưu; xử lý prefix theo thứ tự ngược.</details>

## Checklist hoàn thành lộ trình

- [ ] Tự cài được Trie từ đầu và giải Word Search II
- [ ] Tự cài được Union-Find đầy đủ (path compression + union by rank)
- [ ] Thành thạo pattern sliding window biến đổi kích thước (Minimum Window Substring)
- [ ] Nhớ được các bit trick cơ bản và biết khi nào dùng XOR để tối ưu bộ nhớ

**Sau khi hoàn thành toàn bộ 11 chủ đề**: hãy thử luyện tập tổng hợp trên LeetCode theo độ khó Easy → Medium → Hard, ưu tiên các bài thuộc chủ đề bạn còn yếu nhất.
