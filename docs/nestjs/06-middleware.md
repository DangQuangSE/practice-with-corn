# Bài 06 — Middleware

## Kiến thức cần nắm

- Middleware chạy **trước** route handler, giống Express middleware — phù hợp cho logging, kiểm tra header thô, đo thời gian xử lý.
- Hai cách viết: function middleware đơn giản, hoặc class implement `NestMiddleware` (có thể inject dependency).
- Áp dụng middleware trong `configure()` của module implement `NestModule`, dùng `consumer.apply(...).forRoutes(...)`.
- Khác Guard/Interceptor: middleware không biết route handler nào sẽ chạy, không truy cập được `ExecutionContext`.

## Bài tập

1. Tạo `LoggerMiddleware` (class, implement `NestMiddleware`) ghi log dạng:
   `[2026-06-21T10:00:00.000Z] GET /books - 12ms` (đo thời gian bằng cách hook vào `res.on('finish', ...)`).
2. Áp dụng middleware này cho toàn bộ route trong `AppModule`:
   ```ts
   export class AppModule implements NestModule {
     configure(consumer: MiddlewareConsumer) {
       consumer.apply(LoggerMiddleware).forRoutes('*');
     }
   }
   ```
3. Tạo thêm `ApiKeyMiddleware` chỉ áp dụng cho `BooksModule`: kiểm tra header `x-api-key` có khớp giá trị cố định (tạm hardcode, sẽ thay bằng env ở bài 13) không; nếu sai thì gọi `res.status(401).json(...)` và không gọi `next()`.
4. Dùng `forRoutes({ path: 'books', method: RequestMethod.POST })` để áp `ApiKeyMiddleware` chỉ cho route tạo sách (POST), các route GET vẫn public.
5. Test: gọi `POST /books` không có header `x-api-key` → nhận 401; có header đúng → tạo sách thành công.

## Thử thách thêm

- So sánh middleware vs Guard (bài 08) vs Interceptor (bài 09) bằng một bảng tự viết: thời điểm chạy, có truy cập route handler info không, dùng cho việc gì là hợp lý nhất.
- Dùng `consumer.apply(LoggerMiddleware).exclude('health').forRoutes('*')` để loại trừ route `/health` khỏi logging.
