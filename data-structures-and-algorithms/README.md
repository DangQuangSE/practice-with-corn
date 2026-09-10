# Code luyện tập DSA

Nơi viết lời giải cho các bài tập trong [../docs/dsa-practice/](../docs/dsa-practice/).
Tài liệu (lý thuyết + đề bài + gợi ý) nằm bên `docs/`, code nằm ở đây.

## Cách chạy (Java)

Không cần Maven, không cần compile. JDK 22+ chạy thẳng file nguồn và tự biên dịch
các class liên quan trong cùng thư mục:

```bash
cd data-structures-and-algorithms/01-arrays-and-strings
java TwoSum.java
```

Kết quả:

```
TwoSum
  FAIL  basic example
        expected: [0, 1]
        actual:   []
  ...
1 pass, 4 fail
```

Exit code là `1` khi còn test trượt, `0` khi pass hết — sau này muốn gắn CI thì dùng luôn được.

**Tên test cố ý viết bằng tiếng Anh.** Terminal Windows mặc định chạy code page 850, đọc byte
UTF-8 thành ký tự rác nên tiếng Việt in ra sẽ vỡ. Giữ toàn bộ phần *in ra màn hình* ở ASCII là
cách gọn nhất, khỏi phải cấu hình gì. Comment và javadoc trong file vẫn để tiếng Việt bình thường
vì VSCode hiển thị đúng.

## Chủ đề 1 — Mảng & Chuỗi

Đề bài đầy đủ kèm gợi ý: [../docs/dsa-practice/01-arrays-and-strings/java.md](../docs/dsa-practice/01-arrays-and-strings/java.md)

| Bài | File | Kỹ thuật cần dùng |
|---|---|---|
| 1.1 | [MaxMin.java](01-arrays-and-strings/MaxMin.java) | Duyệt 1 lần |
| 1.2 | [Palindrome.java](01-arrays-and-strings/Palindrome.java) | Two pointers |
| 1.3 | [ReverseString.java](01-arrays-and-strings/ReverseString.java) | Two pointers |
| 1.4 | [TwoSum.java](01-arrays-and-strings/TwoSum.java) | HashMap |
| 1.5 | [RotateArray.java](01-arrays-and-strings/RotateArray.java) | Đảo ngược 3 lần, in-place |
| 1.6 | [LongestUniqueSubstring.java](01-arrays-and-strings/LongestUniqueSubstring.java) | Sliding window |
| 1.7 | [MaxSubarray.java](01-arrays-and-strings/MaxSubarray.java) | Kadane |
| 1.8 | [ProductExceptSelf.java](01-arrays-and-strings/ProductExceptSelf.java) | Prefix / suffix product |
| 1.9 | [TrappingRainWater.java](01-arrays-and-strings/TrappingRainWater.java) | Two pointers |

Test đã viết sẵn, bạn chỉ cần điền phần `solve()`. Một vài test có thể pass ngay từ đầu
do trùng với giá trị trả về mặc định của khung (ví dụ hàm trả `0` mà đáp án đúng cũng là `0`) —
đừng nhầm đó là đã xong, cứ nhìn vào dòng tổng kết.

## Quy trình làm một bài

1. Tạo file mới trong thư mục chủ đề, tên trùng tên class (ví dụ `KadaneMaxSubarray.java`).
2. Copy khung từ [01-arrays-and-strings/TwoSum.java](01-arrays-and-strings/TwoSum.java):
   một hàm `solve(...)` để trống + `main()` chứa các `Check.expect(...)`.
3. **Viết test trước khi viết lời giải.** Nghĩ sẵn các edge case: mảng rỗng, 1 phần tử,
   phần tử trùng nhau, số âm, không có kết quả.
4. Chạy `java TenFile.java` → thấy FAIL đỏ.
5. Code phần `solve` cho tới khi xanh hết.
6. Ghi độ phức tạp thời gian/bộ nhớ vào comment đầu hàm — đây là phần hay bị bỏ qua nhất
   nhưng lại là thứ người phỏng vấn hỏi.

## Bộ kiểm thử

[Check.java](01-arrays-and-strings/Check.java) là một helper tự viết, không phụ thuộc thư viện ngoài:

| Hàm | Công dụng |
|---|---|
| `Check.expect(tên, thực tế, mong đợi)` | So sánh và in PASS/FAIL. Xử lý được cả mảng và mảng lồng nhau. |
| `Check.summary()` | In tổng kết, thoát với exit code khác 0 nếu có test trượt. |

Mỗi thư mục chủ đề cần một bản copy của `Check.java` (vì các file không dùng package,
Java chỉ tìm class trong cùng thư mục nguồn).

## Node.js

Node 22 có sẵn test runner, không cần cài gì:

```js
import assert from "node:assert";
import { test } from "node:test";

test("two sum — ví dụ cơ bản", () => {
  assert.deepStrictEqual(solve([2, 7, 11, 15], 9), [0, 1]);
});
```

Chạy bằng `node --test`.
