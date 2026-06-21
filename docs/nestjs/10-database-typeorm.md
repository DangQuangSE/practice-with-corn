# Bài 10 — Database với TypeORM

## Kiến thức cần nắm

- Đây là bước chuyển toàn bộ resource từ mảng in-memory sang dữ liệu thật trong database.
- `@nestjs/typeorm` cung cấp `TypeOrmModule.forRoot()` (kết nối DB) và `TypeOrmModule.forFeature([Entity])` (đăng ký repository cho từng module).
- Entity là class với `@Entity()`, field dùng `@Column()`, khoá chính `@PrimaryGeneratedColumn()`.
- `Repository<T>` (inject qua `@InjectRepository(Entity)`) cung cấp method: `find()`, `findOneBy()`, `save()`, `update()`, `delete()`.
- SQLite phù hợp cho luyện tập (không cần cài server riêng): `type: 'sqlite', database: 'library.sqlite'`.

## Bài tập

1. Cài: `npm i @nestjs/typeorm typeorm sqlite3`.
2. Cấu hình `TypeOrmModule.forRoot({ type: 'sqlite', database: 'library.sqlite', entities: [...], synchronize: true })` trong `AppModule`. (`synchronize: true` chỉ dùng khi luyện tập, không dùng ở production.)
3. Tạo `Book` entity (`book.entity.ts`): `id` (PK auto), `title`, `author` (tạm để string, sẽ chuyển quan hệ thật ở bài 11), `year`, `available` (default true).
4. Trong `BooksModule`, thêm `TypeOrmModule.forFeature([Book])` vào `imports`.
5. Viết lại `BooksService` dùng `Repository<Book>` thay cho mảng in-memory:
   - `findAll()` → `this.bookRepo.find()`
   - `findOne(id)` → `this.bookRepo.findOneBy({ id })`, throw `NotFoundException` nếu null (giữ lại logic từ bài 07).
   - `create(dto)` → `this.bookRepo.save(this.bookRepo.create(dto))`
   - `update(id, dto)` → kiểm tra tồn tại, `this.bookRepo.update(id, dto)`, trả lại bản ghi mới.
   - `remove(id)` → kiểm tra tồn tại, `this.bookRepo.delete(id)`.
6. Làm tương tự cho `Author` và `Category`.
7. Xác nhận lại toàn bộ test thủ công từ các bài trước (CRUD, validation, exception, auth) vẫn hoạt động đúng với dữ liệu thật trong SQLite.

## Thử thách thêm

- Thử đổi sang PostgreSQL (dùng Docker: `docker run -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres`), so sánh config khác gì so với SQLite.
- Tìm hiểu TypeORM Migration (`typeorm migration:generate`) thay cho `synchronize: true`, hiểu vì sao migration an toàn hơn cho production.
