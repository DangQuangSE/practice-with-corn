# 2. Sliding Window, Kadane, Prefix & Suffix — Java

Đây là bộ bài luyện tập Medium tập trung vào bốn pattern thường gặp khi xử lý
mảng và chuỗi. Code Java tương ứng nằm trong
`data-structures-and-algorithms/02-sliding-window-kadane-prefix-suffix/`.

## Bài tập

### Sliding window

**Bài 2.1 — Minimum Size Subarray Sum**

Cho `nums` chỉ gồm số dương và `target`, tìm độ dài nhỏ nhất của dãy con liên tiếp có tổng lớn hơn hoặc bằng `target`. Không có dãy phù hợp thì trả về `0`.

Input: `nums=[2,3,1,2,4,3], target=7` → Output: `2`

<details><summary>Gợi ý</summary>Mở rộng `right` để tổng đạt target, sau đó tăng `left` chừng nào vẫn còn hợp lệ để tìm cửa sổ ngắn nhất. Vì tất cả số đều dương nên tổng giảm đơn điệu khi tăng `left`.</details>

---

**Bài 2.2 — Permutation in String**

Kiểm tra `s2` có chứa substring là một hoán vị của `s1` hay không. Chuỗi chỉ gồm chữ cái thường tiếng Anh.

Input: `s1="ab", s2="eidbaooo"` → Output: `true`

<details><summary>Gợi ý</summary>Dùng cửa sổ kích thước cố định bằng `s1.length()`. Giữ hai mảng tần suất 26 phần tử và cập nhật khi cửa sổ trượt.</details>

---

**Bài 2.3 — Longest Repeating Character Replacement**

Với tối đa `k` lần thay ký tự, tìm độ dài substring dài nhất có thể biến thành toàn cùng một ký tự.

Input: `s="AABABBA", k=1` → Output: `4`

<details><summary>Gợi ý</summary>Trong cửa sổ, nếu `windowLength - maxFrequency > k` thì cửa sổ không hợp lệ. `maxFrequency` là số lần xuất hiện nhiều nhất của một ký tự trong cửa sổ.</details>

### Kadane và các biến thể

**Bài 2.4 — Maximum Product Subarray**

Tìm tích lớn nhất của một dãy con liên tiếp không rỗng.

Input: `nums=[2,3,-2,4]` → Output: `6`

<details><summary>Gợi ý</summary>Giữ cả `maxProduct` và `minProduct` kết thúc tại vị trí hiện tại. Khi gặp số âm, hai giá trị này sẽ đổi vai trò.</details>

---

**Bài 2.5 — Maximum Sum Circular Subarray**

Tìm tổng lớn nhất của dãy con liên tiếp trong mảng vòng; dãy con có thể nối từ cuối mảng về đầu mảng.

Input: `nums=[5,-3,5]` → Output: `10`

<details><summary>Gợi ý</summary>Kết quả là `max(kadaneMax, total - kadaneMin)`. Trường hợp mọi phần tử âm phải trả về `kadaneMax`, vì dãy con không được rỗng.</details>

---

**Bài 2.6 — Maximum Subarray Sum with One Deletion**

Được phép xóa tối đa một phần tử trong dãy con. Tìm tổng lớn nhất của dãy con sau khi xóa.

Input: `nums=[1,-2,0,3]` → Output: `4`

<details><summary>Gợi ý</summary>Dùng hai trạng thái chạy từ trái sang phải: tổng tốt nhất kết thúc tại vị trí hiện tại khi chưa xóa và khi đã xóa một phần tử.</details>

### Prefix / suffix

**Bài 2.7 — Subarray Sum Equals K**

Đếm số dãy con liên tiếp có tổng đúng bằng `k`; mảng có thể chứa số âm và số 0.

Input: `nums=[1,1,1], k=2` → Output: `2`

<details><summary>Gợi ý</summary>Nếu prefix hiện tại là `sum`, mọi prefix trước đó bằng `sum-k` tạo ra một dãy con có tổng k. Lưu số lần xuất hiện prefix bằng HashMap và khởi tạo prefix 0 xuất hiện 1 lần.</details>

---

**Bài 2.8 — Partition Array into Disjoint Intervals**

Chia mảng thành `left` và `right` không rỗng sao cho mọi phần tử của `left` nhỏ hơn hoặc bằng mọi phần tử của `right`. Trả về độ dài nhỏ nhất của `left`.

Input: `[5,0,3,8,6]` → Output: `3`

<details><summary>Gợi ý</summary>Tính `suffixMin[i]`, sau đó duyệt prefix và tìm vị trí đầu tiên mà `prefixMax <= suffixMin[i+1]`.</details>

---

**Bài 2.9 — Shortest Subarray to Remove to Make Array Sorted**

Xóa một đoạn liên tiếp ngắn nhất (đoạn rỗng được phép) để phần còn lại không giảm.

Input: `[1,2,3,10,4,2,3,5]` → Output: `3`

<details><summary>Gợi ý</summary>Tìm đoạn không giảm dài nhất ở đầu và ở cuối. Sau đó dùng hai con trỏ để thử nối từng phần tử của prefix với suffix; phần ở giữa là đoạn cần xóa.</details>

---

**Bài 2.10 — Maximum Average Subarray I** (Medium)

Tìm giá trị trung bình lớn nhất của một dãy con liên tiếp có đúng k phần tử.

Input: nums=[1,12,-5,-6,50,3], k=4 -> Output: 12.75

<details><summary>Gợi ý</summary>Tính tổng cửa sổ đầu tiên, sau đó mỗi bước trừ phần tử rời khỏi cửa sổ và cộng phần tử mới. Chỉ cần giữ tổng lớn nhất.</details>

## Checklist

- [ ] Phân biệt sliding window kích thước cố định và kích thước biến đổi
- [ ] Giải thích vì sao Kadane tích phải lưu cả giá trị lớn nhất và nhỏ nhất
- [ ] Nhận diện khi prefix sum cần kết hợp với HashMap
- [ ] Dùng prefix max / suffix min để kiểm tra điều kiện trên toàn hai phía
