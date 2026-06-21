# Bài 11 — Quan hệ dữ liệu & Pagination

## Kiến thức cần nắm

- Quan hệ One-to-Many: `@OneToMany()` / `@ManyToOne()` — một `Author` có nhiều `Book`.
- Quan hệ Many-to-Many: `@ManyToMany()` + `@JoinTable()` — một `Book` thuộc nhiều `Category`, một `Category` chứa nhiều `Book`.
- Load quan hệ: truyền `relations: ['author', 'categories']` vào `find()`/`findOneBy()`, hoặc dùng `QueryBuilder` cho truy vấn phức tạp hơn.
- Pagination kiểu offset: `skip`/`take` trong TypeORM tương ứng `page`/`limit` từ query string.
- Filter/sort qua query: `?author=Tolkien&sort=year:desc&page=1&limit=10`.

## Bài tập

1. Đổi `Book.author` (string) thành quan hệ thật: `@ManyToOne(() => Author, author => author.books) author: Author;` + cột `authorId`. Thêm `@OneToMany(() => Book, book => book.author) books: Book[]` ở `Author`.
2. Cập nhật `CreateBookDto`: nhận `authorId` (number) thay vì `author` (string). Service khi tạo sách phải tìm `Author` theo `authorId`, throw `NotFoundException` nếu không tồn tại.
3. Thêm quan hệ Many-to-Many `Book` ↔ `Category` (một sách có thể thuộc nhiều thể loại). `CreateBookDto.categoryIds: number[]`.
4. Cập nhật `findOne`/`findAll` của `BooksService` để load kèm `relations: ['author', 'categories']`, và response trả về author dạng object lồng (`{ id, name }`) thay vì chỉ id.
5. Thêm pagination cho `GET /books`: query `page` (default 1), `limit` (default 10, tối đa 100). Response dạng:
   ```json
   { "data": [...], "meta": { "page": 1, "limit": 10, "total": 42, "totalPages": 5 } }
   ```
6. Thêm filter `?authorId=3` và `?categoryId=2`, sort `?sort=year:asc` hoặc `?sort=title:desc` (parse string này thành `order` object của TypeORM).
7. Validate query params bằng một `ListBooksQueryDto` riêng (kết hợp kiến thức bài 05), dùng `ParseIntPipe`/`@IsOptional()` cho `page`, `limit`.

## Thử thách thêm

- Thêm entity `BorrowRecord` (`bookId`, `userId`, `borrowedAt`, `returnedAt | null`) thể hiện quan hệ Many-to-Many gián tiếp giữa `User` và `Book` qua bảng trung gian có thêm dữ liệu riêng (ngày mượn/trả) — TypeORM gọi đây là "join table with extra columns", thường implement bằng entity riêng thay vì `@ManyToMany` thuần.
- Viết route `POST /books/:id/borrow` (cần `JwtAuthGuard` từ bài 08): tạo `BorrowRecord`, đồng thời set `book.available = false`; nếu sách đang `available = false`, throw `BookNotAvailableException` đã chuẩn bị ở bài 07.
