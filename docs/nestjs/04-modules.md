# Bài 04 — Modules

## Kiến thức cần nắm

- `@Module({ imports, controllers, providers, exports })` là đơn vị tổ chức code theo tính năng (feature module).
- `AppModule` là module gốc, import các feature module khác vào.
- Muốn một provider của module A dùng được ở module B, module A phải `export` provider đó, và module B phải `import` module A.
- `@Global()` đánh dấu module dùng chung toàn app, không cần import lại ở mọi nơi (dùng hạn chế, chỉ cho thứ thực sự global như config/logger).

## Bài tập

1. Generate module riêng cho từng resource đã có:
   ```
   nest g module books
   nest g module authors
   ```
   Đảm bảo `nest g` tự động gắn `BooksController`/`BooksService` vào `BooksModule` (nếu generate thủ công thì tự khai báo trong `@Module`).
2. Trong `AppModule`, import `BooksModule` và `AuthorsModule` thay vì khai báo `BooksController`/`BooksService` trực tiếp.
3. `BooksService` đang phụ thuộc `AuthorsService` (từ bài 03) — đảm bảo `AuthorsModule` export `AuthorsService`, và `BooksModule` import `AuthorsModule`. Chạy lại app, xác nhận DI vẫn hoạt động qua module boundary.
4. Tạo `CategoriesModule` mới với `Category` resource đơn giản (id, name) — CRUD in-memory tương tự Book.
5. Tạo một `SharedModule` chứa một `LoggerService` tự viết (in ra console có timestamp), export nó, rồi import `SharedModule` vào cả `BooksModule` và `AuthorsModule` để dùng chung.

## Thử thách thêm

- Thử đặt `LoggerService` là `@Global()` thay vì export qua `SharedModule`, so sánh sự khác biệt khi sử dụng ở module mới mà không cần import.
- Vẽ sơ đồ dependency giữa các module hiện tại (`AppModule → BooksModule → AuthorsModule`, `SharedModule`...) để chắc chắn không có import vòng tròn (circular dependency). Nếu gặp circular dependency, tìm hiểu `forwardRef()`.
