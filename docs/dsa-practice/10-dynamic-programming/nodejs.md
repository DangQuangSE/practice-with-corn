# 10. Quy hoạch động (Dynamic Programming) — Node.js (JavaScript)

## Lý thuyết

Khái niệm và khung tư duy giải DP giống Java hoàn toàn — DP là về tư duy toán học, không phụ thuộc ngôn ngữ:

1. **Overlapping subproblems** + **Optimal substructure**.
2. **Top-down (Memoization)**: đệ quy + cache (`Map` hoặc array).
3. **Bottom-up (Tabulation)**: xây bảng `dp[]` từ base case.

### Khung tư duy giải bài DP

1. Định nghĩa rõ `dp[i]` đại diện cho cái gì.
2. Tìm base case.
3. Tìm công thức truy hồi.
4. Xác định thứ tự tính.
5. Đáp án cuối nằm ở đâu trong bảng dp?

```js
// Ví dụ: Fibonacci bottom-up
function fib(n) {
  if (n <= 1) return n;
  const dp = new Array(n + 1).fill(0);
  dp[1] = 1;
  for (let i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
  }
  return dp[n];
}
```

⚠️ Lưu ý: số trong JS là double 64-bit, với bài DP liên quan số rất lớn (giai thừa, Fibonacci xa) cần cẩn thận độ chính xác — dùng `BigInt` nếu cần.

## Bài tập

### Mức cơ bản

**Bài 10.1 — Climbing Stairs**

Mỗi bước có thể leo 1 hoặc 2 bậc thang, hỏi có bao nhiêu cách leo hết n bậc.

Input: `n=3` → Output: `3`

<details><summary>Gợi ý</summary>`dp[i] = dp[i-1] + dp[i-2]` — giống Fibonacci.</details>

---

**Bài 10.2 — House Robber**

Cho mảng giá trị nhà, không được trộm 2 nhà liền kề, tìm tổng lớn nhất có thể trộm.

Input: `[1,2,3,1]` → Output: `4`

<details><summary>Gợi ý</summary>`dp[i] = max(dp[i-1], dp[i-2] + nums[i])`.</details>

---

**Bài 10.3 — Min Cost Climbing Stairs**

<details><summary>Gợi ý</summary>`dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2])`.</details>

### Mức trung bình

**Bài 10.4 — Coin Change**

Input: `coins=[1,2,5], amount=11` → Output: `3`

<details><summary>Gợi ý</summary>`dp[i]` = số xu ít nhất để đủ số tiền i, khởi tạo `Infinity`, `dp[0] = 0`. Với mỗi i, thử từng coin: `dp[i] = Math.min(dp[i], dp[i - coin] + 1)`.</details>

---

**Bài 10.5 — Longest Increasing Subsequence (LIS)**

Input: `[10,9,2,5,3,7,101,18]` → Output: `4`

<details><summary>Gợi ý</summary>O(n²): `dp[i] = max(dp[j] + 1)` với mọi `j < i` mà `nums[j] < nums[i]`. Tối ưu O(n log n) dùng binary search trên mảng "tails" — tìm hiểu thêm nếu muốn nâng cao.</details>

---

**Bài 10.6 — 0/1 Knapsack (Cái túi 0/1)**

<details><summary>Gợi ý</summary>`dp[i][w]` = giá trị lớn nhất dùng i vật đầu, dung tích còn lại w. Công thức truy hồi giống Java. Có thể tối ưu xuống dp 1 chiều, duyệt w từ lớn đến nhỏ để tránh dùng lại vật cùng vòng lặp.</details>

---

**Bài 10.7 — Longest Common Subsequence (LCS)**

Input: `text1="abcde", text2="ace"` → Output: `3`

<details><summary>Gợi ý</summary>`dp[i][j]` dựa trên ký tự cuối của 2 chuỗi con giống/khác nhau, giống công thức Java.</details>

### Mức nâng cao

**Bài 10.8 — Edit Distance**

<details><summary>Gợi ý</summary>`dp[i][j]` = số bước ít nhất biến `word1[0..i)` thành `word2[0..j)`. Nếu ký tự cuối giống: `dp[i][j] = dp[i-1][j-1]`. Khác: `1 + Math.min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])`.</details>

---

**Bài 10.9 — Partition Equal Subset Sum**

<details><summary>Gợi ý</summary>Quy về Subset Sum: kiểm tra có tập con tổng = `totalSum / 2` không. Biến thể 0/1 Knapsack với `dp[s]` là boolean.</details>

---

**Bài 10.10 — Word Break**

Input: `s="leetcode", wordDict=["leet","code"]` → Output: `true`

<details><summary>Gợi ý</summary>`dp[i]` = true nếu `s.slice(0, i)` tách được từ wordDict. Dùng `Set(wordDict)` để tra cứu O(1).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu rõ 5 bước khung tư duy giải DP và áp dụng được cho bài mới chưa từng gặp
- [ ] Tự làm được House Robber, Coin Change, LIS từ đầu
- [ ] Làm được ít nhất 1 bài DP 2 chiều (Knapsack/LCS/Edit Distance)
- [ ] Phân biệt được top-down memoization và bottom-up tabulation
