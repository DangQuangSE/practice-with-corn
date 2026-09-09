# 9. Đồ thị (Graphs) — Node.js (JavaScript)

## Lý thuyết

Khái niệm giống Java: Adjacency List/Matrix, DFS, BFS, Topological Sort, Union-Find, Dijkstra. Khác biệt chính: dùng `Map`/`Set`/array thay cho các cấu trúc Java, và tự cài PriorityQueue (xem chủ đề 8) khi cần cho Dijkstra.

```js
// Biểu diễn đồ thị bằng adjacency list
const graph = new Map();
function addEdge(u, v) {
  if (!graph.has(u)) graph.set(u, []);
  graph.get(u).push(v);
}
```

## Code mẫu — Khung DFS và BFS

```js
function dfs(graph, node, visited = new Set()) {
  if (visited.has(node)) return;
  visited.add(node);
  // xử lý node ở đây
  for (const neighbor of graph.get(node) ?? []) {
    dfs(graph, neighbor, visited);
  }
}

function bfs(graph, start) {
  const visited = new Set([start]);
  const queue = [start];
  let head = 0;
  while (head < queue.length) {
    const node = queue[head++];
    // xử lý node ở đây
    for (const neighbor of graph.get(node) ?? []) {
      if (!visited.has(neighbor)) {
        visited.add(neighbor);
        queue.push(neighbor);
      }
    }
  }
}
```

## Bài tập

### Mức cơ bản

**Bài 9.1 — Số lượng thành phần liên thông (Number of Connected Components)**

<details><summary>Gợi ý</summary>Duyệt từng node chưa thăm, DFS/BFS để quét hết 1 thành phần, tăng biến đếm mỗi khi bắt đầu lượt duyệt mới.</details>

---

**Bài 9.2 — Kiểm tra đường đi giữa 2 node (Path Exists)**

<details><summary>Gợi ý</summary>BFS hoặc DFS từ node nguồn, kiểm tra có thăm được node đích không.</details>

---

**Bài 9.3 — Số đảo (Number of Islands)**

Cho lưới 2D gồm '1' (đất) và '0' (nước), đếm số đảo (các ô '1' liền kề theo 4 hướng tạo thành 1 đảo).

<details><summary>Gợi ý</summary>Khi gặp '1' chưa thăm, chạy DFS/BFS đánh dấu toàn bộ đảo đó đã thăm, tăng biến đếm.</details>

### Mức trung bình

**Bài 9.4 — Clone Graph**

<details><summary>Gợi ý</summary>DFS/BFS kèm `Map` (node gốc → node clone) để tránh clone lại 1 node nhiều lần.</details>

---

**Bài 9.5 — Course Schedule (Topological Sort / Phát hiện chu trình)**

Cho n khóa học và danh sách `[a, b]` nghĩa là phải học b trước a. Kiểm tra có thể học hết tất cả khóa học không.

<details><summary>Gợi ý</summary>Kahn's Algorithm: tính in-degree mỗi node, BFS từ các node in-degree = 0, giảm in-degree hàng xóm khi xử lý xong. Hoặc DFS với 3 trạng thái (chưa thăm/đang thăm/đã xong) để phát hiện chu trình.</details>

---

**Bài 9.6 — Rotting Oranges (BFS đa nguồn)**

Lưới 2D với 0 (trống), 1 (cam tươi), 2 (cam thối). Tính số phút để toàn bộ cam thối hết.

<details><summary>Gợi ý</summary>Đưa tất cả cam thối vào queue cùng lúc làm level 0, BFS theo từng "phút", đếm số phút tới khi hết queue. Kiểm tra còn cam tươi sót lại không.</details>

### Mức nâng cao

**Bài 9.7 — Dijkstra's Algorithm**

<details><summary>Gợi ý</summary>Dùng class `Heap` đã cài ở chủ đề 8 làm min-heap lưu `[distance, node]`. Luôn xử lý node có distance nhỏ nhất trước, relax khoảng cách hàng xóm. Độ phức tạp: O((V+E) log V).</details>

---

**Bài 9.8 — Union-Find / Redundant Connection**

<details><summary>Gợi ý</summary>Tự cài Union-Find (`find`, `union`) với path compression + union by rank, dùng array làm `parent[]`. Duyệt từng cạnh, nếu 2 đỉnh đã cùng root trước khi union thì đó là cạnh dư.</details>

---

**Bài 9.9 — Word Ladder**

<details><summary>Gợi ý</summary>BFS từ `beginWord`, mỗi bước sinh hàng xóm bằng cách thử thay từng vị trí ký tự (26 chữ cái) và kiểm tra có trong `Set(wordList)` không — tránh so sánh từng cặp từ O(n²).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự code được DFS và BFS từ đầu (cả đệ quy và lặp)
- [ ] Làm được Number of Islands và Course Schedule
- [ ] Hiểu Union-Find với path compression + union by rank
- [ ] Hiểu Dijkstra's Algorithm, biết JS cần tự cài heap để dùng hiệu quả
