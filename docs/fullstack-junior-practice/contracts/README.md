# Shared briefs và API contracts

Thư mục này chứa brief/API contract dùng chung cho bài `Pair`. Một contract mô tả đúng ranh giới giữa Java backend và Next.js frontend; nó không tạo ra một ứng dụng tích lũy dùng chung cho mọi bài.

Mỗi contract được đặt tên theo ID (ví dụ `BE-01.md`) và phải ghi rõ:

- Bối cảnh và mục tiêu học tập.
- Backend owner và frontend owner; mỗi bên có thể chạy/kiểm tra độc lập.
- Endpoint, method, request/response DTO, status code và error shape.
- Quy tắc auth, validation, pagination hoặc retry nếu có.
- Mock/stub contract và dữ liệu synthetic để không phụ thuộc dịch vụ thật.

Tạo contract từ [`templates/shared-contract.md`](../templates/shared-contract.md). Bài `Standalone` không cần contract chung.
