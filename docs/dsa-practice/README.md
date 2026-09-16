# Ôn luyện Cấu trúc dữ liệu & Giải thuật (Java + Node.js)

Bộ tài liệu này dùng để ôn luyện CTDL-GT một cách bài bản, đi từ **cơ bản → trung bình → nâng cao**.
Mỗi chủ đề có 2 file riêng cho **Java** và **Node.js** (JavaScript), cùng cấu trúc để bạn so sánh cách triển khai giữa 2 ngôn ngữ.

## Cách dùng tài liệu này

1. Đọc phần **Lý thuyết** trước để nắm bản chất, độ phức tạp (Big-O), khi nào dùng.
2. Đọc phần **Code mẫu** để hiểu cách triển khai từ đầu (không dùng thư viện có sẵn nếu là cấu trúc dữ liệu).
3. Làm phần **Bài tập** theo thứ tự từ dễ đến khó. Mỗi bài có:
   - Đề bài + ví dụ input/output
   - Gợi ý (để trong `<details>`, chỉ nên mở khi đã suy nghĩ kỹ và bị bí)
   - **Không có đáp án đầy đủ** — mục tiêu là tự code, tự test, tự debug. Nếu cần đối chiếu, hãy chạy thử trên LeetCode hoặc nhờ review riêng.
4. Tự code lại bằng cả 2 ngôn ngữ để thấy sự khác biệt (Java có kiểu tĩnh, quản lý bộ nhớ thủ công hơn; JS linh hoạt, dùng object/array động).

## Lộ trình học (khuyến nghị theo thứ tự)

| # | Chủ đề | File Java | File Node.js | Độ khó |
|---|--------|-----------|--------------|--------|
| 1 | Mảng & Chuỗi (Arrays & Strings) | [java.md](01-arrays-and-strings/java.md) | [nodejs.md](01-arrays-and-strings/nodejs.md) | Cơ bản |
| 2 | Danh sách liên kết (Linked List) | [java.md](02-linked-list/java.md) | [nodejs.md](02-linked-list/nodejs.md) | Cơ bản |
| 3 | Stack & Queue | [java.md](03-stack-queue/java.md) | [nodejs.md](03-stack-queue/nodejs.md) | Cơ bản |
| 4 | Hashing (HashMap/HashSet) | [java.md](04-hashing/java.md) | [nodejs.md](04-hashing/nodejs.md) | Cơ bản |
| 5 | Đệ quy & Quay lui (Recursion & Backtracking) | [java.md](05-recursion-backtracking/java.md) | [nodejs.md](05-recursion-backtracking/nodejs.md) | Trung bình |
| 6 | Sắp xếp & Tìm kiếm (Sorting & Searching) | [java.md](06-sorting-searching/java.md) | [nodejs.md](06-sorting-searching/nodejs.md) | Trung bình |
| 7 | Cây (Trees / BST) | [java.md](07-trees/java.md) | [nodejs.md](07-trees/nodejs.md) | Trung bình |
| 8 | Heap & Priority Queue | [java.md](08-heap/java.md) | [nodejs.md](08-heap/nodejs.md) | Trung bình |
| 9 | Đồ thị (Graphs) | [java.md](09-graphs/java.md) | [nodejs.md](09-graphs/nodejs.md) | Nâng cao |
| 10 | Quy hoạch động (Dynamic Programming) | [java.md](10-dynamic-programming/java.md) | [nodejs.md](10-dynamic-programming/nodejs.md) | Nâng cao |
| 11 | Chủ đề nâng cao (Trie, Union-Find, Sliding Window, Bit Manipulation) | [java.md](11-advanced/java.md) | [nodejs.md](11-advanced/nodejs.md) | Nâng cao |

## Bộ bài bổ sung

| Chủ đề | Java | Node.js | Độ khó |
|---|---|---|---|
| Sliding Window, Kadane, Prefix & Suffix | [java.md](02-sliding-window-kadane-prefix-suffix/java.md) | — | Medium |

## Bảng độ phức tạp cần nhớ (Big-O cheat sheet)

| Cấu trúc dữ liệu | Truy cập | Tìm kiếm | Thêm | Xóa |
|---|---|---|---|---|
| Array | O(1) | O(n) | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1) | O(1) |
| Stack/Queue | O(n) | O(n) | O(1) | O(1) |
| HashMap/HashSet | - | O(1)* | O(1)* | O(1)* |
| BST (cân bằng) | O(log n) | O(log n) | O(log n) | O(log n) |
| Heap | - | O(n) | O(log n) | O(log n) |

\* Trường hợp trung bình, xấu nhất có thể là O(n) nếu va đập hash (collision) nhiều.

| Thuật toán sắp xếp | Tốt nhất | Trung bình | Xấu nhất | Bộ nhớ | Ổn định? |
|---|---|---|---|---|---|
| Bubble Sort | O(n) | O(n²) | O(n²) | O(1) | Có |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | Không |
| Insertion Sort | O(n) | O(n²) | O(n²) | O(1) | Có |
| Merge Sort | O(n log n) | O(n log n) | O(n log n) | O(n) | Có |
| Quick Sort | O(n log n) | O(n log n) | O(n²) | O(log n) | Không |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | Không |

## Mẹo làm bài (Problem-solving framework)

1. **Hiểu đề**: input/output là gì, ràng buộc (constraints) cỡ bao lớn (n ≤ 10? 10^5? 10^9?) — cỡ ràng buộc gợi ý độ phức tạp cần đạt.
2. **Brute-force trước**: nghĩ ra cách chậm nhưng đúng, sau đó tối ưu.
3. **Nhận diện pattern**: hai con trỏ (two pointers), sliding window, prefix sum, đệ quy, quy hoạch động, BFS/DFS...
4. **Code & test**: thử với input nhỏ, edge case (rỗng, 1 phần tử, trùng lặp, âm...).
5. **Phân tích lại độ phức tạp** sau khi code xong.

## Môi trường chạy thử

- **Java**: cần JDK (≥ 11). Compile: `javac Main.java` rồi `java Main`. Hoặc dùng [LeetCode](https://leetcode.com), [JDoodle](https://www.jdoodle.com) để chạy nhanh.
- **Node.js**: cần Node (≥ 16). Chạy trực tiếp: `node bai-tap.js`.
