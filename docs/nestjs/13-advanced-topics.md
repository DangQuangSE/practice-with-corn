# Bài 13 — Chủ đề nâng cao

## Kiến thức cần nắm

- `@nestjs/config`: load `.env` qua `ConfigModule.forRoot()`, inject `ConfigService` thay vì đọc `process.env` trực tiếp khắp nơi.
- `@nestjs/swagger`: tự sinh API docs từ decorator (`@ApiTags`, `@ApiProperty`, `@ApiResponse`) — rất hữu ích để tự kiểm tra lại toàn bộ API đã xây từ bài 02-11.
- `@nestjs/cache-manager`: cache có TTL, thay cho `CacheInterceptor` tự viết ở bài 09.
- `@nestjs/schedule`: cron job định kỳ trong app (ví dụ dọn dữ liệu, gửi nhắc nhở).
- Upload file: `@nestjs/platform-express` + `multer` qua `FileInterceptor`.

## Bài tập

1. **Config**: cài `@nestjs/config`, chuyển toàn bộ giá trị hardcode (cổng server, JWT secret ở bài 08, API key ở bài 06, đường dẫn SQLite ở bài 10) sang file `.env`, đọc qua `ConfigService`. Đảm bảo `.env` được thêm vào `.gitignore`.
2. **Swagger**: cài `@nestjs/swagger`, setup `SwaggerModule.setup('api/docs', app, document)`. Gắn `@ApiTags('books')` cho `BooksController`, `@ApiProperty()` cho field trong DTO. Mở `http://localhost:3000/api/docs`, thử gọi thử vài API ngay trên UI.
3. **Caching**: cài `@nestjs/cache-manager`, thay `CacheInterceptor` tự viết ở bài 09 bằng bản chính thức của Nest cho route `GET /books`, set TTL 30s.
4. **Cron job**: cài `@nestjs/schedule`, viết một `@Cron('0 0 * * *')` (chạy lúc nửa đêm) tự động đánh dấu các `BorrowRecord` quá hạn 14 ngày chưa trả là "overdue" (thêm field `status` vào entity nếu cần).
5. **Upload file**: thêm route `POST /books/:id/cover` nhận file ảnh bìa sách qua `FileInterceptor('file')`, lưu vào thư mục `uploads/`, cập nhật field `coverUrl` của `Book`. Validate chỉ nhận `.jpg/.png`, giới hạn dung lượng 2MB.

## Thử thách thêm

- Đóng gói toàn bộ app bằng Docker: viết `Dockerfile` + `docker-compose.yml` (app + Postgres) để chạy `library-api` không cần cài Node/DB trực tiếp trên máy.
- Tìm hiểu `@nestjs/throttler` để rate-limit route `POST /auth/login` (chống brute-force), liên kết lại với kiến thức Guard ở bài 08.
- Nhìn lại toàn bộ 13 bài: viết một file `ARCHITECTURE.md` ngắn mô tả cấu trúc cuối cùng của `library-api` (module nào chứa gì, luồng request đi qua các lớp nào) — đây là bài tập tổng hợp tốt để chắc chắn đã nắm được toàn cảnh.
