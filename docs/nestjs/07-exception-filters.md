# Bài 07 — Exception Filters

## Kiến thức cần nắm

- Nest có sẵn các exception class: `NotFoundException`, `BadRequestException`, `ForbiddenException`, `UnauthorizedException`, `ConflictException`... đều kế thừa `HttpException`.
- Throw exception ngay trong service/controller, Nest tự bắt và convert thành response JSON chuẩn `{ statusCode, message, error }`.
- `@Catch(ExceptionType)` + class implement `ExceptionFilter` để custom format lỗi hoặc log lỗi tập trung.
- Filter áp dụng theo thứ tự: method-level (`@UseFilters()` trên handler) → controller-level → global (`app.useGlobalFilters()`).

## Bài tập

1. Sửa `BooksService.findOne(id)`: nếu không tìm thấy sách, `throw new NotFoundException(\`Book #${id} not found\`)` thay vì trả `null` (dọn lại phần tạm bợ từ bài 02).
2. Áp dụng tương tự cho `update()` và `remove()` — không thể sửa/xoá sách không tồn tại.
3. Trong `BooksService.create()`, nếu sách với `title` + `author` đã tồn tại, `throw new ConflictException('Book already exists')`.
4. Viết `HttpExceptionFilter` implement `ExceptionFilter`, bắt `HttpException`, format lại response thành:
   ```json
   { "success": false, "statusCode": 404, "message": "Book #5 not found", "path": "/books/5", "timestamp": "..." }
   ```
5. Đăng ký filter này global trong `main.ts` (`app.useGlobalFilters(new HttpExceptionFilter())`).
6. Viết thêm `AllExceptionsFilter` bắt `@Catch()` (không truyền type → bắt mọi lỗi, kể cả lỗi không phải HttpException) để tránh leak stack trace ra client, trả về 500 generic kèm log lỗi gốc ra console/logger.

## Thử thách thêm

- Tạo custom exception class `BookNotAvailableException` (kế thừa `BadRequestException`) dùng riêng cho luồng mượn sách (chuẩn bị cho bài 11 — `BorrowRecord`).
- Tìm hiểu cách Nest xử lý lỗi validation (từ `ValidationPipe` ở bài 05) có đi qua exception filter không, và thử custom lại format lỗi validation cho đồng nhất với format lỗi ở bài này.
