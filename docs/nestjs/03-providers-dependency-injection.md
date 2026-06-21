# Bài 03 — Providers & Dependency Injection

## Kiến thức cần nắm

- Provider là class được đánh dấu `@Injectable()`, do Nest IoC container tạo và quản lý instance (mặc định là singleton).
- Inject provider vào nơi khác qua constructor: `constructor(private readonly booksService: BooksService) {}`.
- Tách logic nghiệp vụ ra Service giúp Controller chỉ còn vai trò "định tuyến + gọi service", dễ test và tái sử dụng.

## Bài tập

1. Generate service: `nest g service books`.
2. Chuyển toàn bộ logic xử lý mảng `books` (đọc/thêm/sửa/xoá/lọc) từ `BooksController` (bài 02) sang `BooksService`. Các method gợi ý:
   - `findAll(author?: string)`
   - `findOne(id: number)`
   - `create(data: any)`
   - `update(id: number, data: any)`
   - `remove(id: number)`
   - `countAll()`
3. Inject `BooksService` vào `BooksController` qua constructor, mỗi route handler giờ chỉ gọi method tương ứng của service.
4. Tạo thêm `AuthorsService` đơn giản (mảng in-memory tên tác giả) và inject nó vào `BooksService` — minh hoạ một service có thể phụ thuộc service khác.
   - Trong `BooksService.create()`, kiểm tra tác giả có tồn tại trong `AuthorsService` chưa trước khi tạo sách; nếu chưa có, tự thêm tác giả mới.

## Thử thách thêm

- Đánh dấu `BooksService` với scope `REQUEST` (`@Injectable({ scope: Scope.REQUEST })`) và quan sát sự khác biệt: instance được tạo lại mỗi request thay vì singleton. Suy nghĩ khi nào nên dùng scope này (gợi ý: cần lưu dữ liệu riêng theo từng request, ví dụ user hiện tại).
- Thử inject một giá trị thường (không phải class) bằng custom provider: `{ provide: 'LIBRARY_NAME', useValue: 'Thư viện Quận 1' }` và inject bằng `@Inject('LIBRARY_NAME')`.
