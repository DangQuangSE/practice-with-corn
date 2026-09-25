# Java Business Rules — Spring Boot

Track backend gồm các bài Java/Spring Boot ngắn, độc lập theo ID trong [lộ trình](../docs/fullstack-junior-practice/index.md). Mỗi bài có prompt riêng và TODO; không cần hoàn thiện bài trước để bắt đầu bài sau.

## Quy ước

- Bài paired lưu brief/API contract trong `docs/fullstack-junior-practice/contracts/`; phần backend thuộc bài nằm trong `java-business-rules/exercises/<ID>/`.
- Bài standalone tự mô tả input, output, acceptance criteria và cách chạy.
- Mỗi module runnable pin JDK, Spring Boot và dependency trong `pom.xml`; module không import code của bài khác.
- Dùng DTO thay vì trả persistence entity trực tiếp; secret chỉ lấy từ biến môi trường/profile local.
- Fake external service mặc định. Không dùng credential production.

## Chạy bài

Mở README của từng ID; chạy lệnh tại đúng thư mục module. Không có lệnh chạy chung hoặc ứng dụng monolith cho cả curriculum.

Tạo brief theo [`docs/fullstack-junior-practice/templates/java-exercise.md`](../docs/fullstack-junior-practice/templates/java-exercise.md). Danh sách competency: [Java/backend inventory](../plans/fullstack-junior-practice/inventory-map.md#a-java-backend-http-application-structure), [security](../plans/fullstack-junior-practice/inventory-map.md#c-authentication-authorization-security), [workflows](../plans/fullstack-junior-practice/inventory-map.md#d-common-business-workflows), [integrations](../plans/fullstack-junior-practice/inventory-map.md#e-third-party-and-infrastructure-integrations).
