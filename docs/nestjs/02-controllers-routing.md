# Bài 02 — Controllers & Routing

## Kiến thức cần nắm

- `@Controller('books')` định nghĩa prefix route cho cả class.
- Decorator method: `@Get()`, `@Post()`, `@Patch()`, `@Delete()`, `@Put()`.
- Lấy dữ liệu request: `@Param('id')`, `@Query('search')`, `@Body()`, `@Headers()`.
- Mã trạng thái: mặc định `@Post()` trả 201, còn lại trả 200 — đổi bằng `@HttpCode()`.
- Thứ tự route quan trọng: route tĩnh (`/books/featured`) phải khai báo trước route động (`/books/:id`) nếu trùng prefix.

## Bài tập

Tạo resource `Book` (dùng tạm mảng in-memory, chưa cần service riêng — sẽ tách ở bài 03):

```
nest g controller books
```

1. Trong `BooksController`, khai báo một mảng tĩnh `books` (in-memory) với vài object mẫu: `{ id, title, author, year, available }`.
2. Implement các route:
   - `GET /books` — trả toàn bộ danh sách.
   - `GET /books?author=...` — lọc theo tên tác giả qua query param.
   - `GET /books/:id` — trả 1 sách theo id; nếu không tìm thấy, tạm thời trả `null` (sẽ xử lý lỗi chuẩn ở bài 07).
   - `POST /books` — nhận `@Body()` là object sách mới, thêm vào mảng, trả về sách vừa tạo với status 201.
   - `PATCH /books/:id` — cập nhật một phần thông tin sách theo id.
   - `DELETE /books/:id` — xoá sách theo id, trả status 204 (dùng `@HttpCode(204)`).
3. Thêm route `GET /books/stats/count` trả `{ total: number }` — chú ý thứ tự khai báo so với `GET /books/:id` để không bị nuốt route.

## Thử thách thêm

- Thêm route `GET /books/:id/summary` trả về một câu mô tả ngắn ghép từ title + author + year.
- Dùng `@Req()` để lấy nguyên `Request` object và log ra `req.method`, `req.url` trong mỗi handler.
