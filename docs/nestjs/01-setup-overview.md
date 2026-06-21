# Bài 01 — Tổng quan & Setup

## Kiến thức cần nắm

- NestJS là framework Node.js theo kiến trúc module hoá, lấy cảm hứng từ Angular: dùng decorator (`@Module`, `@Controller`, `@Injectable`...) và Dependency Injection.
- Ba khối xây dựng cốt lõi: **Module** (gom nhóm tính năng), **Controller** (nhận request, trả response), **Provider/Service** (chứa logic nghiệp vụ).
- File khởi động: `main.ts` gọi `NestFactory.create(AppModule)` để bootstrap toàn app.

## Bài tập

1. Cài Nest CLI toàn cục: `npm i -g @nestjs/cli`. Kiểm tra `nest --version`.
2. Tạo project mới trong thư mục `nestjs-business-rules/`:
   ```
   nest new library-api
   ```
   Chọn package manager `npm` hoặc `pnpm` tuỳ bạn.
3. Chạy thử app: `npm run start:dev`. Mở `http://localhost:3000`, xác nhận thấy chữ "Hello World!".
4. Khám phá cấu trúc `src/`: đọc qua `app.module.ts`, `app.controller.ts`, `app.service.ts`, `main.ts`. Vẽ sơ đồ (trên giấy hoặc comment) thể hiện luồng: request → `main.ts` → `AppModule` → `AppController` → `AppService` → response.
5. Đổi cổng server từ 3000 sang 4000 bằng cách sửa `main.ts` (`app.listen(4000)`).
6. Thêm một route mới `GET /health` trả về `{ status: 'ok', timestamp: <Date hiện tại> }` ngay trong `AppController`/`AppService` có sẵn.

## Thử thách thêm

- Tìm hiểu `nest g --help` và thử generate thử một resource bằng `nest g resource test` (sẽ dùng kỹ ở bài 02), sau đó xoá nếu chưa cần dùng.
- Đọc về global prefix (`app.setGlobalPrefix('api')`) và áp dụng để mọi route có dạng `/api/...`.
