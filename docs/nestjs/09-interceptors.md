# Bài 09 — Interceptors

## Kiến thức cần nắm

- Interceptor implement `NestInterceptor`, bao quanh việc thực thi handler (giống middleware AOP) — chạy **trước và sau** khi handler xử lý, vì dựa trên RxJS `Observable`.
- Use case phổ biến: log thời gian xử lý, transform/đóng gói response, cache response, bắt lỗi để log (tương tự filter nhưng ở tầng khác).
- `intercept(context, next)`: gọi `next.handle()` để chạy tiếp handler, dùng RxJS operators (`tap`, `map`, `catchError`) trên kết quả trả về.
- Áp dụng qua `@UseInterceptors()` (method/controller) hoặc global qua `app.useGlobalInterceptors()`.

## Bài tập

1. Viết `TransformInterceptor` bọc mọi response thành công vào format chuẩn:
   ```json
   { "success": true, "data": <kết quả gốc>, "timestamp": "..." }
   ```
   dùng RxJS `map()`. Đăng ký global trong `main.ts`.
2. Viết `LoggingInterceptor` đo thời gian xử lý của handler (không phải toàn request như middleware bài 06) bằng `tap()`, log dạng `BooksController.findAll - 3ms`.
3. So sánh: log của `LoggingInterceptor` (chỉ đo thời gian chạy handler) khác gì log của `LoggerMiddleware` ở bài 06 (đo toàn bộ request/response cycle, gồm cả các middleware/guard khác)? Ghi nhận xét ngắn.
4. Viết `CacheInterceptor` đơn giản (tự viết, không dùng `@nestjs/cache-manager` lúc này) cho route `GET /books`: lưu kết quả vào biến `Map` trong interceptor kèm timestamp, nếu request tiếp theo đến trong vòng 10 giây thì trả thẳng cache, không gọi `next.handle()`.
5. Áp dụng `@UseInterceptors(CacheInterceptor)` chỉ cho `GET /books`, không áp cho các route khác.

## Thử thách thêm

- Dùng `catchError()` trong một interceptor để log lỗi (kèm thông tin route) trước khi rethrow, minh hoạ interceptor cũng có thể tham gia xử lý lỗi.
- Tìm hiểu thứ tự thực thi đầy đủ của một request đi qua Nest: Middleware → Guard → Interceptor (before) → Pipe → Handler → Interceptor (after) → Exception Filter (nếu có lỗi). Vẽ lại sơ đồ này dựa trên những gì đã làm ở bài 06-09.
