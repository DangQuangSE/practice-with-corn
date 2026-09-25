# Next.js Business Rules

Track frontend gồm các bài Next.js App Router + TypeScript ngắn, độc lập theo ID trong [lộ trình](../docs/fullstack-junior-practice/index.md). Không có một frontend lớn dùng chung state/code qua các bài.

## Quy ước

- Bài paired dùng brief/API contract tại `docs/fullstack-junior-practice/contracts/`; bài standalone tự khai báo mục tiêu và dữ liệu mock.
- Mỗi bài ghi phiên bản Node/Next.js, scripts và dependency ngay tại module.
- Java backend sở hữu domain/API/auth; frontend gọi Java trực tiếp theo contract, trừ bài BFF extension riêng.
- Mọi trang có trạng thái loading/error/empty phù hợp, keyboard interaction và responsive acceptance criteria.
- Không đưa server secret vào client bundle và không lưu credential/token trong local/session storage.

## Chạy bài

Mở README của từng ID; chạy package script tại đúng thư mục module. Dùng mock API khi backend chưa chạy.

Tạo brief theo [`docs/fullstack-junior-practice/templates/nextjs-exercise.md`](../docs/fullstack-junior-practice/templates/nextjs-exercise.md). Danh sách competency: [Next.js/frontend inventory](../plans/fullstack-junior-practice/inventory-map.md#b-nextjs-and-frontend-integration).
