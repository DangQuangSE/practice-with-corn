# Bài 05 — DTO & Validation Pipes

## Kiến thức cần nắm

- DTO (Data Transfer Object) định nghĩa hình dạng dữ liệu vào/ra, thường là class (không phải interface) để dùng được decorator runtime.
- `class-validator` + `class-transformer`: gắn decorator (`@IsString()`, `@IsInt()`, `@IsOptional()`, `@Min()`...) lên field của DTO.
- `ValidationPipe` (global, qua `app.useGlobalPipes`) tự động validate body/param/query dựa theo DTO, trả lỗi 400 nếu sai.
- `PartialType(CreateBookDto)` (từ `@nestjs/mapped-types`) tạo DTO update với mọi field optional, tránh lặp code.
- Pipe cũng dùng để transform: `ParseIntPipe`, `ParseBoolPipe` cho param/query.

## Bài tập

1. Cài thư viện: `npm i class-validator class-transformer @nestjs/mapped-types`.
2. Trong `main.ts`, bật global pipe:
   ```ts
   app.useGlobalPipes(new ValidationPipe({ whitelist: true, transform: true }));
   ```
   Tìm hiểu ý nghĩa của `whitelist` (loại field lạ không khai báo trong DTO) và `transform` (tự convert kiểu, ví dụ query string "5" → number 5).
3. Tạo `CreateBookDto` trong `books/dto/create-book.dto.ts` với field: `title` (string, bắt buộc, độ dài 1-200), `author` (string, bắt buộc), `year` (number, từ 1450 đến năm hiện tại), `available` (boolean, optional, default true).
4. Tạo `UpdateBookDto extends PartialType(CreateBookDto)`.
5. Sửa `BooksController`: `create(@Body() dto: CreateBookDto)`, `update(@Param('id', ParseIntPipe) id: number, @Body() dto: UpdateBookDto)`.
6. Test bằng Postman/curl: gửi body thiếu `title` → phải nhận 400 với message rõ field nào lỗi. Gửi `year` âm hoặc field thừa (ví dụ `foo: 'bar'`) → kiểm tra bị từ chối/loại bỏ đúng như kỳ vọng.
7. Áp dụng tương tự cho `Author` và `Category` (DTO + validate).

## Thử thách thêm

- Viết một custom validator (`@ValidatorConstraint`) kiểm tra `title` không được trùng với sách đã tồn tại (gợi ý: cần inject service vào validator hoặc validate ở service layer thay vì decorator thuần).
- Tìm hiểu `class-transformer`'s `@Exclude()`/`@Expose()` để ẩn field nhạy cảm khi trả response (ví dụ chuẩn bị cho `password` ở User resource sau này).
