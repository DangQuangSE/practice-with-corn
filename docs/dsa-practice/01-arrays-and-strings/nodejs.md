# 1. Mảng & Chuỗi — Node.js (JavaScript)

## Lý thuyết

- `Array` trong JS là động (resizable), được triển khai nội bộ linh hoạt (có thể là dense array hoặc hashtable tùy engine V8 tối ưu).
- `String` trong JS cũng **immutable** giống Java — nối chuỗi nhiều lần nên dùng `Array.join()` hoặc template literal hợp lý, tránh `+=` trong loop lớn (V8 thường tối ưu tốt nhưng vẫn nên biết).
- Một số API hữu ích: `Array.prototype.map/filter/reduce/sort`, `Array.from()`, `String.prototype.split/slice/charAt`, `Map`/`Set` (built-in hash structures).
- JS không phân biệt rõ kiểu số nguyên/thực, tất cả là `Number` (double 64-bit) — cẩn thận overflow khi làm bài liên quan số lớn (dùng `BigInt` khi cần).

### Độ phức tạp cần nhớ

| Thao tác | Array | String | Map/Set |
|---|---|---|---|
| Truy cập index | O(1) | O(1) | - |
| `push`/`pop` (cuối) | O(1)* | - | O(1)* |
| `shift`/`unshift` (đầu) | O(n) | - | - |
| `splice` giữa mảng | O(n) | - | - |
| `has`/`get`/`set` | - | - | O(1)* |

## Code mẫu — kỹ thuật Two Pointers

```js
// Đảo ngược mảng tại chỗ (in-place) dùng 2 con trỏ
function reverse(arr) {
  let left = 0, right = arr.length - 1;
  while (left < right) {
    [arr[left], arr[right]] = [arr[right], arr[left]]; // destructuring swap
    left++;
    right--;
  }
  return arr;
}

console.log(reverse([1, 2, 3, 4, 5])); // [5, 4, 3, 2, 1]
```

## Bài tập

### Mức cơ bản

**Bài 1.1 — Tìm giá trị lớn nhất và nhỏ nhất trong mảng**

Input: `[3, 7, 1, 9, 4]` → Output: `{ max: 9, min: 1 }`

<details><summary>Gợi ý</summary>Có thể dùng `Math.max(...arr)` / `Math.min(...arr)` cho mảng nhỏ, nhưng nên tự code bằng vòng lặp để hiểu bản chất O(n) và tránh lỗi "too many arguments" với mảng lớn.</details>

---

**Bài 1.2 — Kiểm tra chuỗi đối xứng (Palindrome)**

Input: `"madam"` → `true`; `"hello"` → `false`.

<details><summary>Gợi ý</summary>Dùng 2 con trỏ, hoặc cách "lười": `s === [...s].reverse().join('')`.</details>

---

**Bài 1.3 — Đảo ngược một chuỗi**

Input: `"corn"` → Output: `"nroc"`

<details><summary>Gợi ý</summary>`s.split('').reverse().join('')` là cách nhanh, nhưng cũng nên tự code bằng vòng lặp/2 con trỏ.</details>

### Mức trung bình

**Bài 1.4 — Two Sum**

Cho mảng `nums` và số `target`, tìm 2 chỉ số `i, j` sao cho `nums[i] + nums[j] === target`.

Input: `nums=[2,7,11,15], target=9` → Output: `[0,1]`

<details><summary>Gợi ý</summary>Dùng `Map` để lưu (giá trị → chỉ số) khi duyệt, tra cứu O(1).</details>

---

**Bài 1.5 — Xoay mảng (Rotate Array) sang phải k bước**

Input: `nums=[1,2,3,4,5,6,7], k=3` → Output: `[5,6,7,1,2,3,4]`

<details><summary>Gợi ý</summary>Cách tối ưu in-place: đảo toàn mảng rồi đảo từng nửa, giống Java. Tránh dùng `splice`/`unshift` liên tục vì tốn O(n) mỗi lần gọi.</details>

---

**Bài 1.6 — Tìm chuỗi con không lặp ký tự dài nhất**

Input: `"abcabcbb"` → Output: `3`

<details><summary>Gợi ý</summary>Sliding window với `Map` lưu vị trí cuối cùng đã gặp của từng ký tự.</details>

### Mức nâng cao

**Bài 1.7 — Maximum Subarray (Kadane's Algorithm)**

Input: `[-2,1,-3,4,-1,2,1,-5,4]` → Output: `6`

<details><summary>Gợi ý</summary>Giữ `currentSum`, reset khi nó nhỏ hơn phần tử hiện tại; cập nhật `maxSum` mỗi bước.</details>

---

**Bài 1.8 — Sản phẩm của mảng trừ phần tử hiện tại**

Không dùng phép chia. Input: `[1,2,3,4]` → Output: `[24,12,8,6]`

<details><summary>Gợi ý</summary>Tính prefix product từ trái, rồi nhân với suffix product từ phải.</details>

---

**Bài 1.9 — Trapping Rain Water**

Input: `[0,1,0,2,1,0,1,3,2,1,2,1]` → Output: `6`

<details><summary>Gợi ý</summary>2 con trỏ trái/phải, theo dõi leftMax/rightMax, di chuyển con trỏ có chiều cao nhỏ hơn.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự làm lại được Two Sum không nhìn gợi ý
- [ ] Biết khi nào nên dùng `Map`/`Set` thay vì object thường (`{}`)
- [ ] Làm được Kadane's Algorithm và hiểu vì sao nó là O(n)
- [ ] Hiểu kỹ thuật sliding window và two pointers, so sánh được với cách triển khai Java
