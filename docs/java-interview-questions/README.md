# Bộ câu hỏi phỏng vấn Java (Junior Level) — Kèm đáp án chi tiết

Bộ tài liệu này được biên soạn dưới góc nhìn của một Java Senior chuẩn bị phỏng vấn ứng viên Junior.
Mục tiêu: không kiểm tra khả năng "học thuộc định nghĩa", mà kiểm tra khả năng **hiểu bản chất** và
**áp dụng vào tình huống thực tế / code thực tế**.

## Cách dùng bộ tài liệu này khi phỏng vấn

1. Không hỏi thẳng "X là gì?" — hỏi qua tình huống hoặc đoạn code có bug ngầm.
2. Sau khi ứng viên trả lời, hỏi tiếp "tại sao lại như vậy?" — junior học vẹt sẽ bí ở đây.
3. Yêu cầu viết code nhỏ ngay tại chỗ (whiteboard hoặc editor) cho các câu liên quan đến
   `equals/hashCode`, `try-finally`, `Stream API`.
4. Một junior "hiểu thật" không cần trả lời đúng 100%, nhưng phải có **tư duy truy vấn đúng hướng**
   (ví dụ: không biết chính xác nhưng đoán đúng bản chất Stack/Heap).

## Danh sách chủ đề

| # | Chủ đề | File |
|---|--------|------|
| 1 | OOP căn bản | [01-oop-basics.md](01-oop-basics.md) |
| 2 | Bộ nhớ & kiểu dữ liệu | [02-memory-data-types.md](02-memory-data-types.md) |
| 3 | Collection Framework | [03-collections.md](03-collections.md) |
| 4 | Exception Handling | [04-exception-handling.md](04-exception-handling.md) |
| 5 | JVM & Garbage Collection | [05-jvm-gc.md](05-jvm-gc.md) |
| 6 | Multithreading cơ bản | [06-multithreading.md](06-multithreading.md) |
| 7 | Java 8+ Features | [07-java8-features.md](07-java8-features.md) |

## Tiêu chí đánh giá nhanh (rubric gợi ý)

- **Không đạt**: chỉ đọc lại định nghĩa, không giải thích được "tại sao", không debug được đoạn code ví dụ.
- **Đạt cơ bản**: hiểu đúng bản chất, giải thích được "tại sao", nhưng cần gợi ý nhỏ khi gặp case lạ.
- **Tốt**: tự đưa ra ví dụ riêng, chỉ ra được hệ quả/edge case mà không cần hỏi thêm.
