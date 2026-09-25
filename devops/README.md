# DevOps Practice

## Phase 06 runnable lab

Start with [Docker, Compose, CI, and local operations](exercises/phase-06-operations/README.md). The lab has a Java multi-stage image, Compose health checks and optional local support services, and a build-only GitHub Actions workflow. Destructive volume reset is a separate, explicitly documented exercise.

Các lab tập trung vào local development và CI; không cần production access. Lab độc lập, có lệnh thao tác và kết quả quan sát được. Những file `dockerFile` và `dockerCompose` hiện tại được giữ làm ví dụ nguồn để review/sửa trong phase DevOps.

## Quy ước

- Không commit secret thật; dùng `.env.example` cho tên biến và giá trị giả.
- Ghi rõ prerequisite (Docker, Java, Node), thư mục chạy lệnh, command và expected result.
- Nêu riêng thao tác xóa volume/reset vì hành động đó có thể xóa dữ liệu local.
- Dùng [`docs/fullstack-junior-practice/templates/devops-lab.md`](../docs/fullstack-junior-practice/templates/devops-lab.md) cho lab mới.

Inventory: [DevOps and local operations](../plans/fullstack-junior-practice/inventory-map.md#h-devops-and-local-operations).
