# 10. Quy hoạch động (Dynamic Programming) — Java

## Lý thuyết

DP áp dụng khi bài toán có:
1. **Overlapping subproblems**: các bài toán con lặp lại nhiều lần (ví dụ Fibonacci đệ quy thường tính lại `fib(3)` nhiều lần).
2. **Optimal substructure**: lời giải tối ưu của bài toán lớn được xây từ lời giải tối ưu của bài toán con.

### 2 cách triển khai

- **Top-down (Memoization)**: đệ quy + cache kết quả (HashMap hoặc array).
- **Bottom-up (Tabulation)**: xây bảng `dp[]` từ base case, lặp tới đáp án cuối — thường nhanh hơn vì không tốn overhead đệ quy.

### Khung tư duy giải bài DP

1. Định nghĩa rõ `dp[i]` (hoặc `dp[i][j]`) đại diện cho cái gì.
2. Tìm base case.
3. Tìm công thức truy hồi (transition) — `dp[i]` được tính từ những `dp[j]` nào (j < i)?
4. Xác định thứ tự tính (thường từ nhỏ đến lớn).
5. Đáp án cuối nằm ở đâu trong bảng dp?

```java
// Ví dụ: Fibonacci bottom-up
static long fib(int n) {
    if (n <= 1) return n;
    long[] dp = new long[n + 1];
    dp[0] = 0; dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
}
```

## Bài tập

### Mức cơ bản

**Bài 10.1 — Climbing Stairs**

Mỗi bước có thể leo 1 hoặc 2 bậc thang, hỏi có bao nhiêu cách leo hết n bậc.

Input: `n=3` → Output: `3` (1+1+1, 1+2, 2+1)

<details><summary>Gợi ý</summary>`dp[i] = dp[i-1] + dp[i-2]` — giống Fibonacci. `dp[i]` = số cách leo tới bậc i.</details>

---

**Bài 10.2 — House Robber**

Cho mảng giá trị nhà, không được trộm 2 nhà liền kề, tìm tổng lớn nhất có thể trộm.

Input: `[1,2,3,1]` → Output: `4` (trộm nhà 0 và nhà 2)

<details><summary>Gợi ý</summary>`dp[i]` = số tiền lớn nhất trộm được tính tới nhà i. `dp[i] = max(dp[i-1], dp[i-2] + nums[i])` — chọn không trộm nhà i (giữ dp[i-1]) hoặc trộm nhà i (cộng dp[i-2]).</details>

---

**Bài 10.3 — Min Cost Climbing Stairs**

Mỗi bậc thang có 1 chi phí, tìm chi phí nhỏ nhất để leo hết thang (có thể bắt đầu từ bậc 0 hoặc 1, mỗi lần leo 1 hoặc 2 bậc).

<details><summary>Gợi ý</summary>`dp[i]` = chi phí nhỏ nhất để tới bậc i. `dp[i] = cost[i] + min(dp[i-1], dp[i-2])`.</details>

### Mức trung bình

**Bài 10.4 — Coin Change**

Cho các loại tiền xu và số tiền `amount`, tìm số xu ít nhất để đủ amount (hoặc -1 nếu không thể).

Input: `coins=[1,2,5], amount=11` → Output: `3` (5+5+1)

<details><summary>Gợi ý</summary>`dp[i]` = số xu ít nhất để đủ số tiền i. Base case `dp[0] = 0`. Với mỗi `i` từ 1 đến amount, thử từng coin: `dp[i] = min(dp[i], dp[i - coin] + 1)` nếu `i - coin >= 0` và `dp[i-coin]` không phải vô hạn.</details>

---

**Bài 10.5 — Longest Increasing Subsequence (LIS)**

Tìm độ dài dãy con tăng dần dài nhất (không cần liền kề).

Input: `[10,9,2,5,3,7,101,18]` → Output: `4` (dãy `2,3,7,101` hoặc `2,3,7,18`)

<details><summary>Gợi ý</summary>Cách O(n²): `dp[i]` = độ dài LIS kết thúc tại i. `dp[i] = max(dp[j] + 1)` với mọi `j < i` mà `nums[j] < nums[i]`. Cách tối ưu O(n log n): dùng binary search trên mảng "tails" — tìm hiểu thêm nếu muốn nâng cao.</details>

---

**Bài 10.6 — 0/1 Knapsack (Cái túi 0/1)**

Cho các vật có trọng lượng và giá trị, túi có dung tích tối đa W, mỗi vật chỉ được lấy 0 hoặc 1 lần. Tìm giá trị lớn nhất có thể mang.

<details><summary>Gợi ý</summary>`dp[i][w]` = giá trị lớn nhất dùng i vật đầu, dung tích còn lại w. `dp[i][w] = max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i])` nếu `w >= weight[i]`, ngược lại `dp[i][w] = dp[i-1][w]`. Có thể tối ưu xuống dp 1 chiều (duyệt w từ lớn đến nhỏ).</details>

---

**Bài 10.7 — Longest Common Subsequence (LCS)**

Tìm độ dài dãy con chung dài nhất giữa 2 chuỗi.

Input: `text1="abcde", text2="ace"` → Output: `3` (dãy `"ace"`)

<details><summary>Gợi ý</summary>`dp[i][j]` = độ dài LCS của `text1[0..i)` và `text2[0..j)`. Nếu `text1[i-1] == text2[j-1]`: `dp[i][j] = dp[i-1][j-1] + 1`. Ngược lại: `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`.</details>

### Mức nâng cao

**Bài 10.8 — Edit Distance**

Tìm số bước ít nhất (thêm/xóa/sửa 1 ký tự) để biến chuỗi `word1` thành `word2`.

<details><summary>Gợi ý</summary>`dp[i][j]` = số bước ít nhất biến `word1[0..i)` thành `word2[0..j)`. Nếu ký tự cuối giống nhau: `dp[i][j] = dp[i-1][j-1]`. Nếu khác: `dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])` (xóa, thêm, sửa).</details>

---

**Bài 10.9 — Partition Equal Subset Sum**

Kiểm tra có thể chia mảng thành 2 tập con có tổng bằng nhau không.

<details><summary>Gợi ý</summary>Quy về bài toán Subset Sum: kiểm tra có tập con nào có tổng = `totalSum / 2` không (nếu totalSum lẻ thì luôn không thể). Đây là biến thể của 0/1 Knapsack với `dp[s]` = boolean "có thể đạt tổng s hay không".</details>

---

**Bài 10.10 — Word Break**

Cho chuỗi `s` và `wordDict`, kiểm tra `s` có thể được tách thành các từ trong `wordDict` không.

Input: `s="leetcode", wordDict=["leet","code"]` → Output: `true`

<details><summary>Gợi ý</summary>`dp[i]` = true nếu `s[0..i)` có thể tách được từ wordDict. `dp[0] = true`. Với mỗi i, thử mọi `j < i`: nếu `dp[j]` true và `s[j..i)` có trong wordDict thì `dp[i] = true`. Dùng HashSet cho wordDict để tra cứu O(1).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu rõ 5 bước khung tư duy giải DP và áp dụng được cho bài mới chưa từng gặp
- [ ] Tự làm được House Robber, Coin Change, LIS từ đầu
- [ ] Làm được ít nhất 1 bài DP 2 chiều (Knapsack/LCS/Edit Distance)
- [ ] Phân biệt được top-down memoization và bottom-up tabulation, biết khi nào ưu tiên cách nào
