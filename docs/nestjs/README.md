# Lộ trình bài tập NestJS

Bộ bài tập này giúp bạn làm quen và hiểu sâu NestJS thông qua một project xuyên suốt:
**Library API** — hệ thống quản lý thư viện sách (Author, Book, Category, User, Borrow record...).

Mỗi bài học = 1 file. Bài sau dùng lại code của bài trước, nên hãy làm theo thứ tự.
Code thực tế nên được viết trong thư mục [`nestjs-business-rules/`](../../nestjs-business-rules/) ở gốc repo
(tạo project bằng `nest new library-api` hoặc tương đương) — `docs/nestjs/` chỉ chứa đề bài và lý thuyết tóm tắt.

## Lộ trình

| # | Bài học | Chủ đề chính |
|---|---------|--------------|
| 00 | [Khởi động: CRUD Todo List](00-todolist-warmup.md) | CRUD tối giản — làm quen nhanh trước khi vào project chính (tuỳ chọn) |
| 01 | [Tổng quan & Setup](01-setup-overview.md) | Nest CLI, cấu trúc project, chạy app đầu tiên |
| 02 | [Controllers & Routing](02-controllers-routing.md) | Route, param, query, body, status code |
| 03 | [Providers & Dependency Injection](03-providers-dependency-injection.md) | Service, @Injectable, DI |
| 04 | [Modules](04-modules.md) | Tổ chức feature module, imports/exports, shared module |
| 05 | [DTO & Validation Pipes](05-dto-validation-pipes.md) | class-validator, ValidationPipe, PartialType |
| 06 | [Middleware](06-middleware.md) | Logger middleware, áp dụng theo route |
| 07 | [Exception Filters](07-exception-filters.md) | HttpException, custom filter, error response chuẩn hoá |
| 08 | [Guards & Authentication](08-guards-auth.md) | JWT, AuthGuard, phân quyền theo Role |
| 09 | [Interceptors](09-interceptors.md) | Logging, transform response, caching |
| 10 | [Database với TypeORM](10-database-typeorm.md) | Kết nối DB, Entity, Repository, thay in-memory |
| 11 | [Quan hệ dữ liệu & Pagination](11-relations-pagination.md) | 1-N, N-N, filter, sort, phân trang |
| 12 | [Testing](12-testing.md) | Unit test (Jest), e2e test (Supertest) |
| 13 | [Chủ đề nâng cao](13-advanced-topics.md) | Config, Swagger, Caching, Cron job, Upload file |

## Cách làm mỗi bài

1. Đọc phần "Kiến thức cần nắm" — tóm tắt, không thay thế docs chính thức.
2. Làm các bài tập theo thứ tự (mỗi bài tập build trên bài trước).
3. Thử phần "Thử thách thêm" nếu muốn đào sâu.
4. Tài liệu chính thức: https://docs.nestjs.com — tra cứu khi bí.

## Quy ước chung cho toàn bộ project Library API

- Entity chính: `Author`, `Book`, `Category`, `User`, `BorrowRecord`.
- Mỗi resource có CRUD cơ bản: `GET /books`, `GET /books/:id`, `POST /books`, `PATCH /books/:id`, `DELETE /books/:id`.
- Từ bài 05 trở đi, mọi input đều phải có DTO + validate.
- Từ bài 10 trở đi, dữ liệu chuyển từ in-memory array sang database thật (SQLite/PostgreSQL đều được — SQLite tiện cho luyện tập vì không cần cài server).
