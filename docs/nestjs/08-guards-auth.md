# Bài 08 — Guards & Authentication

## Kiến thức cần nắm

- Guard implement `CanActivate`, chạy sau middleware nhưng trước pipe/handler — quyết định request có được tiếp tục xử lý không (`return true/false` hoặc throw exception).
- Guard có quyền truy cập `ExecutionContext` → lấy được route handler, class, request — phù hợp cho auth/role-check (khác middleware ở bài 06).
- Luồng JWT phổ biến: `POST /auth/login` → verify username/password → trả `access_token` → client gửi token qua header `Authorization: Bearer <token>` → `JwtAuthGuard` verify token ở các route cần bảo vệ.
- `@UseGuards(AuthGuard)` áp dụng ở method/controller/global. Custom decorator `@Roles('admin')` + `RolesGuard` để phân quyền.
- Dùng `@nestjs/passport` + `passport-jwt` là cách chuẩn của Nest, nhưng tự viết guard verify JWT thủ công (dùng `@nestjs/jwt`) cũng là bài tập tốt để hiểu cơ chế.

## Bài tập

1. Tạo `User` resource đơn giản (in-memory): `{ id, username, password (hash), role: 'admin' | 'member' }`. Dùng `bcrypt` để hash password khi tạo user seed mẫu.
2. Cài `@nestjs/jwt`. Tạo `AuthModule` với `AuthService.login(username, password)`:
   - Tìm user theo username, so sánh password bằng `bcrypt.compare`.
   - Nếu đúng, ký JWT (`jwtService.sign({ sub: user.id, role: user.role })`) và trả `{ access_token }`.
   - Nếu sai, `throw new UnauthorizedException()`.
3. Tạo `AuthController` với route `POST /auth/login`.
4. Viết `JwtAuthGuard` implement `CanActivate`: đọc header `Authorization`, verify token bằng `jwtService.verify()`, gắn payload decode được vào `request.user`; nếu invalid/missing → throw `UnauthorizedException`.
5. Áp `@UseGuards(JwtAuthGuard)` cho route `POST /books`, `PATCH /books/:id`, `DELETE /books/:id` — các route đọc (`GET`) vẫn public.
6. Viết custom decorator `@Roles('admin')` (dùng `SetMetadata`) và `RolesGuard` đọc metadata này + `request.user.role` để chỉ cho phép admin xoá sách (`DELETE /books/:id`).
7. Test đủ 3 case: không có token → 401; có token nhưng role member gọi DELETE → 403; admin gọi DELETE → 200/204.

## Thử thách thêm

- Tạo decorator `@CurrentUser()` (dùng `createParamDecorator`) để lấy `request.user` thẳng trong handler thay vì phải gọi `@Req()` rồi tự lấy `.user`.
- Thêm refresh token flow: `POST /auth/refresh` cấp access token mới từ refresh token còn hạn.
