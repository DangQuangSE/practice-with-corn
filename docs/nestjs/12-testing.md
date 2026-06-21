# Bài 12 — Testing

## Kiến thức cần nắm

- Nest dùng Jest mặc định (`npm run test`, `npm run test:e2e`).
- Unit test cho service: dùng `Test.createTestingModule({ providers: [...] })`, mock dependency bằng `useValue`/`useFactory` (ví dụ mock `Repository` thay vì gọi DB thật).
- e2e test: dùng `Test.createTestingModule({ imports: [AppModule] })` rồi `app.init()`, gửi request thật qua `supertest`.
- Mỗi `describe` nên test cả "happy path" (input hợp lệ) và "edge case" (input sai, không tồn tại, không có quyền...).

## Bài tập

1. Viết unit test cho `BooksService` (`books.service.spec.ts`):
   - Mock `Repository<Book>` bằng object giả lập các method `find`, `findOneBy`, `save`, `update`, `delete` (dùng `jest.fn()`).
   - Test `findAll()` trả đúng danh sách mock.
   - Test `findOne(id)` throw `NotFoundException` khi repository trả `null`.
   - Test `create(dto)` gọi đúng `authorRepo.findOneBy` trước khi `bookRepo.save`.
2. Viết unit test cho `AuthService.login()`:
   - Case sai password → throw `UnauthorizedException`.
   - Case đúng → trả object có field `access_token`.
3. Viết e2e test (`test/books.e2e-spec.ts`) cho luồng đầy đủ:
   - `POST /auth/login` lấy token admin.
   - `POST /books` không có token → 401.
   - `POST /books` có token, body thiếu `title` → 400.
   - `POST /books` có token, body hợp lệ → 201, kiểm tra response có đúng field mong đợi.
   - `GET /books/:id` với id vừa tạo → 200, đúng dữ liệu.
   - `DELETE /books/:id` với token role member → 403.
4. Dùng database SQLite riêng cho test (ví dụ in-memory `database: ':memory:'`) để không ảnh hưởng dữ liệu thật, cấu hình trong `test/jest-e2e.json` hoặc một `AppModule` test riêng.

## Thử thách thêm

- Đo coverage (`npm run test:cov`), đặt mục tiêu coverage tối thiểu cho `services/` (ví dụ 80%).
- Viết test cho `RolesGuard`/`JwtAuthGuard` độc lập (không qua e2e), mock `ExecutionContext` để kiểm tra guard trả đúng true/false theo từng trường hợp role.
