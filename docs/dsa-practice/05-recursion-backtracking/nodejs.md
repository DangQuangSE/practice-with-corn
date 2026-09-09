# 5. Đệ quy & Quay lui (Recursion & Backtracking) — Node.js (JavaScript)

## Lý thuyết

Khái niệm giống Java: cần **base case** và **recursive case**. Một điểm khác biệt quan trọng:

- JS engine (V8) có giới hạn độ sâu call stack thấp hơn Java khá nhiều (thường ~10,000-15,000 frame tùy môi trường) → đệ quy quá sâu dễ gặp `RangeError: Maximum call stack size exceeded`. Với bài toán n lớn, nên ưu tiên cách lặp (iterative) hoặc tail-call nếu có thể (lưu ý: V8 **không** tối ưu tail-call dù ES6 có đặc tả).
- Mẫu chung của backtracking giống Java, chỉ khác cú pháp:

```js
function backtrack(state) {
  if (isGoal(state)) {
    recordSolution(state);
    return;
  }
  for (const choice of getChoices(state)) {
    if (isValid(choice)) {
      apply(choice);          // chọn
      backtrack(state);       // đệ quy
      undo(choice);           // quay lui (rất quan trọng!)
    }
  }
}
```

## Code mẫu — Giai thừa và Fibonacci

```js
function factorial(n) {
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

// Fibonacci đệ quy thường (chậm, O(2^n)) vs có memoization (O(n))
function fibSlow(n) {
  if (n <= 1) return n;
  return fibSlow(n - 1) + fibSlow(n - 2);
}

function fibMemo(n, memo = new Map()) {
  if (n <= 1) return n;
  if (memo.has(n)) return memo.get(n);
  const result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
  memo.set(n, result);
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

<details><summary>Gợi ý</summary>`reverse(s) = reverse(s.slice(1)) + s[0]`, base case là chuỗi rỗng.</details>

---

**Bài 5.3 — Tính lũy thừa x^n bằng đệ quy (tối ưu O(log n))**

<details><summary>Gợi ý</summary>Chia đôi n mỗi lần (fast exponentiation), xử lý n âm bằng `1 / power(x, -n)`.</details>

### Mức trung bình

**Bài 5.4 — Sinh tất cả tổ hợp con (Subsets / Power Set)**

Input: `[1,2,3]` → Output: `[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]`

<details><summary>Gợi ý</summary>Backtracking: với mỗi phần tử có 2 lựa chọn chọn/không chọn. Mỗi state hiện tại trong đệ quy đều là 1 subset hợp lệ.</details>

---

**Bài 5.5 — Sinh tất cả hoán vị (Permutations)**

Input: `[1,2,3]` → Output: `[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]`

<details><summary>Gợi ý</summary>Dùng array `used[]` theo dõi phần tử đã dùng, nhớ quay lui (`current.pop()`, `used[i]=false`).</details>

---

**Bài 5.6 — Combination Sum**

Input: `candidates=[2,3,6,7], target=7` → Output: `[[2,2,3],[7]]`

<details><summary>Gợi ý</summary>Sort mảng trước, backtracking với pruning khi `candidates[i] > remain`. Để được dùng lại 1 phần tử nhiều lần, đệ quy tiếp với index `i` (không phải `i+1`).</details>

### Mức nâng cao

**Bài 5.7 — N-Queens**

<details><summary>Gợi ý</summary>Dùng 3 `Set` cho cols, diag1 (row-col), diag2 (row+col) để kiểm tra O(1) trước khi đặt hậu ở mỗi hàng.</details>

---

**Bài 5.8 — Word Search**

<details><summary>Gợi ý</summary>DFS + backtracking, đánh dấu ô đã thăm tạm thời bằng ký tự đặc biệt, khôi phục lại sau khi quay lui.</details>

---

**Bài 5.9 — Giải Sudoku**

<details><summary>Gợi ý</summary>Tìm ô trống, thử số 1-9, kiểm tra hợp lệ theo hàng/cột/ô 3x3, đệ quy; quay lui bằng cách đặt lại `'.'` nếu không tìm được lời giải từ lựa chọn đó.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Hiểu rõ base case và recursive case
- [ ] Tự làm được Subsets và Permutations bằng backtracking
- [ ] Biết giới hạn call stack trong Node.js khác Java như thế nào, khi nào nên chuyển sang lặp
- [ ] Làm được N-Queens hoặc Word Search
