# 1. Mảng & Chuỗi — Java

## Lý thuyết

- **Array** trong Java có kích thước cố định khi khai báo (`int[] arr = new int[10]`). Muốn mảng động dùng `ArrayList<T>`.
- Truy cập theo chỉ số: O(1). Chèn/xóa giữa mảng: O(n) vì phải dời phần tử.
- **String** trong Java là **immutable** (bất biến) — mỗi lần nối chuỗi (`+`) sẽ tạo object mới → dùng `StringBuilder` khi nối nhiều lần trong vòng lặp để tránh O(n²).
- Một số API hữu ích: `Arrays.sort()`, `Arrays.fill()`, `Collections.reverse()`, `String.charAt()`, `String.substring()`, `String.toCharArray()`.

### Độ phức tạp cần nhớ

| Thao tác | Array | ArrayList | String | StringBuilder |
|---|---|---|---|---|
| Truy cập index | O(1) | O(1) | O(1) | O(1) |
| Thêm cuối | - | O(1)* | O(n) (tạo mới) | O(1)* |
| Chèn giữa | O(n) | O(n) | O(n) | O(n) |
| Nối chuỗi trong loop n lần | - | - | O(n²) | O(n) |

## Code mẫu — kỹ thuật Two Pointers

```java
public class TwoPointersDemo {
    // Đảo ngược mảng tại chỗ (in-place) dùng 2 con trỏ
    public static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        reverse(arr);
        System.out.println(Arrays.toString(arr)); // [5, 4, 3, 2, 1]
    }
}
```

## Bài tập

### Mức cơ bản

**Bài 1.1 — Tìm giá trị lớn nhất và nhỏ nhất trong mảng**

Input: `[3, 7, 1, 9, 4]` → Output: `max=9, min=1`

<details><summary>Gợi ý</summary>Chạy 1 lần qua mảng, lưu max/min ban đầu là arr[0].</details>

---

**Bài 1.2 — Kiểm tra chuỗi đối xứng (Palindrome)**

Input: `"madam"` → `true`; `"hello"` → `false`. Bỏ qua hoa/thường và khoảng trắng cho mức nâng cao (ví dụ `"A man a plan a canal Panama"`).

<details><summary>Gợi ý</summary>Dùng 2 con trỏ từ 2 đầu chuỗi, so sánh và thu hẹp dần vào giữa.</details>

---

**Bài 1.3 — Đảo ngược một chuỗi**

Input: `"corn"` → Output: `"nroc"`

<details><summary>Gợi ý</summary>Dùng `StringBuilder.reverse()`, hoặc tự code bằng 2 con trỏ để hiểu bản chất.</details>

### Mức trung bình

**Bài 1.4 — Two Sum**

Cho mảng `nums` và số `target`, tìm 2 chỉ số `i, j` sao cho `nums[i] + nums[j] == target`.

Input: `nums=[2,7,11,15], target=9` → Output: `[0,1]`

<details><summary>Gợi ý</summary>Brute-force O(n²) là 2 vòng lặp lồng. Tối ưu O(n) bằng HashMap lưu (giá trị → chỉ số) khi duyệt qua từng phần tử.</details>

---

**Bài 1.5 — Xoay mảng (Rotate Array) sang phải k bước**

Input: `nums=[1,2,3,4,5,6,7], k=3` → Output: `[5,6,7,1,2,3,4]`

<details><summary>Gợi ý</summary>Cách dễ: dùng mảng phụ. Cách tối ưu O(1) bộ nhớ: đảo ngược toàn mảng, rồi đảo ngược từng nửa.</details>

---

**Bài 1.6 — Tìm chuỗi con không lặp ký tự dài nhất (Longest Substring Without Repeating Characters)**

Input: `"abcabcbb"` → Output: `3` (chuỗi `"abc"`)

<details><summary>Gợi ý</summary>Dùng kỹ thuật **sliding window** với HashSet/HashMap lưu ký tự đã gặp trong window hiện tại, thu hẹp window từ bên trái khi gặp ký tự trùng.</details>

### Mức nâng cao

**Bài 1.7 — Maximum Subarray (Kadane's Algorithm)**

Tìm tổng lớn nhất của một dãy con liên tiếp.

Input: `[-2,1,-3,4,-1,2,1,-5,4]` → Output: `6` (dãy `[4,-1,2,1]`)

<details><summary>Gợi ý</summary>Duyệt mảng, giữ `currentSum`: nếu cộng thêm phần tử hiện tại làm currentSum < phần tử đó thì reset currentSum = phần tử đó. Cập nhật `maxSum` mỗi bước. Đây là bài toán nền tảng cho Dynamic Programming.</details>

---

**Bài 1.8 — Sản phẩm của mảng trừ phần tử hiện tại (Product of Array Except Self)**

Không dùng phép chia. Input: `[1,2,3,4]` → Output: `[24,12,8,6]`

<details><summary>Gợi ý</summary>Tính mảng `prefix[i]` = tích các phần tử bên trái i, mảng `suffix[i]` = tích các phần tử bên phải i. Kết quả `result[i] = prefix[i] * suffix[i]`. Có thể tối ưu để chỉ dùng O(1) bộ nhớ phụ.</details>

---

**Bài 1.9 — Trapping Rain Water (giữ nước mưa)**

Cho mảng độ cao các cột, tính tổng lượng nước mưa có thể giữ lại giữa các cột.

Input: `[0,1,0,2,1,0,1,3,2,1,2,1]` → Output: `6`

<details><summary>Gợi ý</summary>Với mỗi vị trí i, lượng nước giữ được = `min(maxLeft[i], maxRight[i]) - height[i]`. Có thể tối ưu bằng 2 con trỏ trái/phải duyệt 1 lần, O(1) bộ nhớ phụ. Đây là bài kinh điển thường gặp trong phỏng vấn.</details>

---

**Bài 1.10 — Three Sum** (Medium)

Tìm mọi bộ ba phần tử có tổng bằng 0; không trả về bộ ba trùng nhau.

Input: [-1,0,1,2,-1,-4] -> Output: [[-1,-1,2],[-1,0,1]]

<details><summary>Gợi ý</summary>Sắp xếp mảng, cố định từng phần tử rồi dùng hai con trỏ cho phần còn lại. Bỏ qua giá trị trùng ở cả vị trí cố định và hai con trỏ.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự làm lại được Two Sum không nhìn gợi ý
- [ ] Giải thích được tại sao StringBuilder nhanh hơn String nối chuỗi trong loop
- [ ] Làm được Kadane's Algorithm và hiểu vì sao nó là O(n)
- [ ] Hiểu kỹ thuật sliding window và two pointers
