# Lộ trình luyện Junior Fullstack Java + Next.js

Curriculum gồm các bài nhỏ, độc lập; mỗi bài có ID ổn định, mục tiêu, phần TODO, tiêu chí nghiệm thu và cách tự kiểm tra. Không có capstone bắt buộc và không có lời giải hoàn chỉnh trong scaffold.

## Bắt đầu

1. Chọn một track bên dưới và đọc README của track.
2. Chọn bài theo nhãn `Core` trước; `Extension` là phần mở rộng tùy chọn.
3. Đọc brief/contract chung nếu bài có nhãn `Pair`; BE và FE vẫn có thể làm, chạy và kiểm tra riêng bằng mock.
4. Hoàn thành TODO, chạy lệnh verification trong bài, rồi tự đối chiếu acceptance criteria.

## Tracks

| Track | Nội dung | Inventory |
|---|---|---|
| [Java Business Rules](../../java-business-rules/README.md) | Spring Boot, HTTP, persistence, security, workflow và tích hợp backend | [A — Java backend, HTTP, application structure](../../plans/fullstack-junior-practice/inventory-map.md#a-java-backend-http-application-structure), [C — Authentication, authorization, security](../../plans/fullstack-junior-practice/inventory-map.md#c-authentication-authorization-security), [D — Common business workflows](../../plans/fullstack-junior-practice/inventory-map.md#d-common-business-workflows), [E — Integrations](../../plans/fullstack-junior-practice/inventory-map.md#e-third-party-and-infrastructure-integrations) |
| [Next.js Business Rules](../../nextjs-business-rules/README.md) | App Router, TypeScript, forms, API clients, accessibility và trạng thái giao diện | [B — Next.js and frontend integration](../../plans/fullstack-junior-practice/inventory-map.md#b-nextjs-and-frontend-integration) |
| [SQL Practice](../../sql-practice/README.md) | PostgreSQL, mô hình dữ liệu, truy vấn, transaction và query plan | [G — PostgreSQL SQL practice](../../plans/fullstack-junior-practice/inventory-map.md#g-sql-practice-postgresql) |
| [DevOps](../../devops/README.md) | Docker, Compose, CI, cấu hình và debug local | [H — DevOps and local operations](../../plans/fullstack-junior-practice/inventory-map.md#h-devops-and-local-operations) |
| [Concept guides](concepts/README.md) | Lý thuyết ngắn gắn với ID bài tập | Các mục A–H trong inventory |
| [Shared contracts](contracts/README.md) | Brief và API contract cho bài Pair | ID Pair trong inventory |

## Theo phase

| Phase | Chủ đề | Bài tập |
|---|---|---|
| 02 | Spring REST và Next.js foundations | [Java exercises](../../java-business-rules/exercises/phase-02-rest-foundations/README.md) · [Next.js exercises](../../nextjs-business-rules/exercises/phase-02-next-foundations/README.md) · [BE-01–BE-10, BE-17–BE-18 inventory](../../plans/fullstack-junior-practice/inventory-map.md#a-java-backend-http-application-structure) · [FE-01–FE-07, FE-12–FE-13 inventory](../../plans/fullstack-junior-practice/inventory-map.md#b-nextjs-and-frontend-integration) |
| 03 | PostgreSQL, JPA và SQL | [Java data-access exercises](../../java-business-rules/exercises/phase-03-data-access/README.md) · [PostgreSQL track and SQL-01–SQL-12](../../sql-practice/README.md) · [BE-11–BE-13, TEST-05 inventory](../../plans/fullstack-junior-practice/inventory-map.md#a-java-backend-http-application-structure) |
| 04 | Authentication và security | [Bearer JWT / identity](../../java-business-rules/exercises/phase-04-identity-security/README.md) · [Browser session](../../java-business-rules/exercises/phase-04-browser-session/README.md) · [Next.js access states](../../nextjs-business-rules/exercises/phase-04-access-states/README.md) · [Session/JWT contracts](contracts/SEC-01-to-06-browser-session.md) · [Security inventory](../../plans/fullstack-junior-practice/inventory-map.md#c-authentication-authorization-security) |
| 05 | Business workflows | [Phase 05 module guide](phase-05-workflows.md) · [FLOW-01, FLOW-03–FLOW-09, FLOW-11, FILE-01, DATA-01, REPORT-01, BE-14–BE-15, FE-09–FE-10, FE-14–FE-15 inventory](../../plans/fullstack-junior-practice/inventory-map.md#d-common-business-workflows) |
| 06 | Testing, Docker và CI | [TEST-01–TEST-08 practice guide](testing-practice.md), [OPS-01–OPS-14 Docker/Compose lab](../../devops/exercises/phase-06-operations/README.md), [inventory](../../plans/fullstack-junior-practice/inventory-map.md#h-devops-and-local-operations) |
| 07 | Service và realtime integrations | [Phase 07 modules and contracts](phase-07-integrations.md) · [inventory](../../plans/fullstack-junior-practice/inventory-map.md#e-third-party-and-infrastructure-integrations) |
| 08 | Payment lifecycle và providers | [Phase 08 Java/Next modules and contract](phase-08-payments.md) · [inventory](../../plans/fullstack-junior-practice/inventory-map.md#d-common-business-workflows) |

## Nguyên tắc an toàn

- Chỉ dùng dữ liệu giả, fake/local adapter hoặc sandbox tùy chọn; không cần production credential hay giao dịch tiền thật.
- Không đưa secret vào frontend/browser, không lưu credential/token trong local/session storage.
- Payment chỉ được xác nhận từ backend đã kiểm tra callback/webhook; redirect trên trình duyệt không phải bằng chứng settlement.
- Mỗi bài pin runtime/framework/dependency riêng trong module runnable; không dựa vào code đã hoàn tất ở bài khác.

## Tham chiếu

- [Competency-to-exercise map](../../plans/fullstack-junior-practice/inventory-map.md)
- [Curriculum specification](../../plans/fullstack-junior-practice/spec.md)
- [Implementation plan](../../plans/fullstack-junior-practice/plan.md)
