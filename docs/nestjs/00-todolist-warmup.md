# Bài 00 — Khởi động: CRUD Todo List (đơn giản)

Bài này tách riêng khỏi project Library API (bài 01-13), mục tiêu là làm một resource **CRUD đơn giản nhất có thể**
để quen tay với cú pháp NestJS trước khi bước vào lộ trình chính. Nếu bạn đã quen Controller/Service/Module rồi,
có thể bỏ qua bài này và vào thẳng [bài 01](01-setup-overview.md).

Có thể làm trong cùng project `library-api`, hoặc tạo project riêng `todo-api` — đều được, mục đích chỉ là luyện phản xạ.

## Kiến thức cần nắm

- Một resource CRUD tối thiểu cần: 1 Controller (route), 1 Service (logic), 1 Module (gom lại) — xem lại bài 02-04 nếu chưa rõ.
- Dữ liệu mẫu cho `Todo`: `{ id: number, title: string, completed: boolean, createdAt: Date }`.

## Bài tập 1 — CRUD in-memory cơ bản

1. `nest g resource todos` (chọn transport `REST API`, chọn **No** khi hỏi có tạo CRUD entry points mẫu hay không nếu muốn tự viết tay; chọn **Yes** nếu muốn xem code mẫu Nest tự sinh rồi sửa lại).
2. Khai báo mảng in-memory `todos: Todo[]` trong `TodosService`, kèm biến `nextId` tự tăng.
3. Implement đủ 5 route trong `TodosController`, gọi qua `TodosService`:
   - `GET /todos` — trả toàn bộ danh sách.
   - `GET /todos/:id` — trả 1 todo, nếu không có thì `throw new NotFoundException()`.
   - `POST /todos` — body `{ title: string }`, tạo todo mới với `completed: false`, `createdAt: new Date()`.
   - `PATCH /todos/:id` — cho phép sửa `title` và/hoặc `completed`.
   - `DELETE /todos/:id` — xoá theo id, trả status 204.
4. Test toàn bộ 5 route bằng curl/Postman, đảm bảo id không tồn tại trả 404 đúng cách.

## Bài tập 2 — Thêm route tiện ích

5. `PATCH /todos/:id/toggle` — đảo ngược giá trị `completed` hiện tại (không cần body).
6. `GET /todos?completed=true` — lọc theo trạng thái hoàn thành qua query param (so sánh string `'true'`/`'false'` hoặc dùng `ParseBoolPipe`).
7. `DELETE /todos/completed` — xoá tất cả todo đã hoàn thành cùng lúc. Chú ý thứ tự khai báo route này phải đứng **trước** `DELETE /todos/:id` để tránh bị route động nuốt mất (giống lưu ý ở bài 02).

## Bài tập 3 — DTO & Validation tối giản

8. Cài `class-validator class-transformer` nếu chưa có, bật `ValidationPipe` global (xem lại bài 05 nếu cần).
9. Tạo `CreateTodoDto` với `title: string` (`@IsString()`, `@IsNotEmpty()`, độ dài tối đa 100).
10. Tạo `UpdateTodoDto` với `title?: string` (optional) và `completed?: boolean` (`@IsOptional() @IsBoolean()`).
11. Test gửi `POST /todos` với `title` rỗng hoặc thiếu → phải nhận 400.

## Bài tập 4 — Sắp xếp & đếm

12. `GET /todos?sort=createdAt:desc` — sắp xếp danh sách theo ngày tạo mới nhất trước (parse string thành field + direction).
13. `GET /todos/stats/summary` — trả `{ total: number, completed: number, pending: number }`. Nhớ khai báo trước `GET /todos/:id`.

## Thử thách thêm (tuỳ chọn — kết nối sang database thật)

- Áp dụng nhanh kiến thức bài 10: chuyển `Todo` từ mảng in-memory sang TypeORM + SQLite, giữ nguyên toàn bộ route đã viết — chỉ đổi phần bên trong `TodosService`.
- Thêm field `priority: 'low' | 'medium' | 'high'` (dùng `@IsEnum()` trong DTO) và route `GET /todos?priority=high`.
- Viết 2-3 unit test cho `TodosService` (xem lại bài 12) trước khi chuyển sang project Library API lớn — đây là lúc tốt để tập phản xạ viết test với một resource nhỏ, ít rủi ro.
