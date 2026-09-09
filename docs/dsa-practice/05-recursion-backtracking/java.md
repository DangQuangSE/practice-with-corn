# 5. Đệ quy & Quay lui (Recursion & Backtracking) — Java

## Lý thuyết

- **Đệ quy (Recursion)**: hàm gọi lại chính nó, cần có **base case** (điều kiện dừng) và **recursive case** (thu nhỏ bài toán). Mỗi lần gọi tốn 1 frame trong call stack → đệ quy sâu có thể StackOverflowError.
- **Backtracking**: kỹ thuật thử từng lựa chọn, nếu không hợp lệ/không dẫn tới lời giải thì "quay lui" (undo) và thử lựa chọn khác. Thường dùng cho bài toán liệt kê tổ hợp/hoán vị/subset, giải Sudoku, N-Queens.
- Mẫu chung của backtracking:

```java
void backtrack(State state) {
    if (isGoal(state)) {
        recordSolution(state);
        return;
    }
    for (Choice choice : getChoices(state)) {
        if (isValid(choice)) {
            apply(choice);          // chọn
            backtrack(state);       // đệ quy
            undo(choice);           // quay lui (rất quan trọng!)
        }
    }
}
```

## Code mẫu — Giai thừa và Fibonacci

```java
static long factorial(int n) {
    if (n <= 1) return 1; // base case
    return n * factorial(n - 1);
}

// Fibonacci đệ quy thường (chậm, O(2^n)) vs có memoization (O(n))
static long fibSlow(int n) {
    if (n <= 1) return n;
    return fibSlow(n - 1) + fibSlow(n - 2);
}

static long fibMemo(int n, Map<Integer, Long> memo) {
    if (n <= 1) return n;
    if (memo.containsKey(n)) return memo.get(n);
    long result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    memo.put(n, result);
    return result;
}
```

## Bài tập

### Mức cơ bản

**Bài 5.1 — Tính tổng các số từ 1 đến n bằng đệ quy**

<details><summary>Gợi ý</summary>Base case: `sum(0) = 0`. Recursive case: `sum(n) = n + sum(n-1)`.</details>

---

**Bài 5.2 — Đảo ngược chuỗi bằng đệ quy**

Input: `"hello"` → Output: `"olleh"`

<details><summary>Gợi ý</summary>`reverse(s) = reverse(s[1:]) + s[0]`, base case là chuỗi rỗng.</details>

---

**Bài 5.3 — Tính lũy thừa x^n bằng đệ quy (tối ưu O(log n))**

<details><summary>Gợi ý</summary>`x^n = (x^(n/2))^2` nếu n chẵn, `x^n = x * x^(n-1)` nếu n lẻ — chia đôi bài toán mỗi lần (fast exponentiation). Đừng quên xử lý n âm.</details>

### Mức trung bình

**Bài 5.4 — Sinh tất cả tổ hợp con (Subsets / Power Set)**

Input: `[1,2,3]` → Output: `[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]`

<details><summary>Gợi ý</summary>Với mỗi phần tử, có 2 lựa chọn: chọn hoặc không chọn vào subset hiện tại → backtracking. Mỗi state hiện tại trong quá trình đệ quy đều là 1 subset hợp lệ cần ghi nhận.</details>

---

**Bài 5.5 — Sinh tất cả hoán vị (Permutations)**

Input: `[1,2,3]` → Output: `[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]`

<details><summary>Gợi ý</summary>Dùng mảng `used[]` để theo dõi phần tử đã dùng trong hoán vị hiện tại. Nhớ quay lui (`used[i]=false`, remove khỏi list) sau mỗi nhánh đệ quy.</details>

---

**Bài 5.6 — Combination Sum**

Cho mảng số dương (có thể dùng lại từng phần tử nhiều lần) và target, tìm mọi tổ hợp có tổng = target.

Input: `candidates=[2,3,6,7], target=7` → Output: `[[2,2,3],[7]]`

<details><summary>Gợi ý</summary>Backtracking, mỗi bước có thể chọn lại cùng phần tử (không tăng index) hoặc chuyển sang phần tử tiếp theo. Sort mảng trước rồi cắt nhánh (pruning) khi tổng vượt target để tăng hiệu năng.</details>

### Mức nâng cao

**Bài 5.7 — N-Queens**

Đặt N quân hậu trên bàn cờ N×N sao cho không quân nào ăn được quân khác.

<details><summary>Gợi ý</summary>Đặt từng hậu theo từng hàng, kiểm tra cột và 2 đường chéo trước khi đặt. Dùng backtracking, set `cols`, `diag1` (row-col), `diag2` (row+col) để kiểm tra O(1).</details>

---

**Bài 5.8 — Word Search (tìm từ trong lưới ký tự)**

Cho lưới ký tự 2D và 1 từ, kiểm tra từ có thể tạo từ các ký tự liền kề (lên/xuống/trái/phải, không lặp ô) hay không.

<details><summary>Gợi ý</summary>DFS + backtracking từ mỗi ô, đánh dấu ô đã dùng tạm thời (ví dụ đổi thành ký tự đặc biệt như `'#'`), sau khi quay lui thì khôi phục lại giá trị gốc.</details>

---

**Bài 5.9 — Giải Sudoku**

<details><summary>Gợi ý</summary>Backtracking: tìm ô trống, thử từng số 1-9, kiểm tra hợp lệ theo hàng/cột/ô vuông 3x3, đệ quy tiếp; nếu không có số hợp lệ nào dẫn tới giải được, quay lui (đặt lại `'.'`).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu rõ base case và recursive case
- [ ] Tự làm được Subsets và Permutations bằng backtracking
- [ ] Hiểu vì sao bước "undo"/"quay lui" là bắt buộc để backtracking đúng
- [ ] Làm được N-Queens hoặc Word Search
