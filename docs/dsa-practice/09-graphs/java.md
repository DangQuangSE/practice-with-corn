# 9. Đồ thị (Graphs) — Java

## Lý thuyết

- **Cách biểu diễn đồ thị**:
  - **Adjacency List** (danh sách kề): `Map<Integer, List<Integer>>` hoặc `List<List<Integer>>` — tiết kiệm bộ nhớ, phổ biến nhất.
  - **Adjacency Matrix**: `int[n][n]` — dễ kiểm tra cạnh O(1) nhưng tốn O(n²) bộ nhớ, chỉ hợp với đồ thị dày đặc.
- **DFS (Depth-First Search)**: dùng đệ quy hoặc stack, đi sâu hết 1 nhánh rồi quay lại.
- **BFS (Breadth-First Search)**: dùng Queue, duyệt theo từng tầng — phù hợp tìm đường đi ngắn nhất trên đồ thị không trọng số.
- **Topological Sort**: sắp xếp các node sao cho mọi cạnh `u -> v` thì `u` đứng trước `v` — chỉ áp dụng cho DAG (Directed Acyclic Graph). 2 cách: DFS + stack, hoặc Kahn's Algorithm (BFS + in-degree).
- **Union-Find (Disjoint Set Union)**: quản lý các nhóm phần tử liên thông, hỗ trợ `union` và `find` gần O(1) nhờ path compression + union by rank.
- **Thuật toán đường đi ngắn nhất có trọng số**: Dijkstra (trọng số không âm, dùng PriorityQueue), Bellman-Ford (cho phép trọng số âm, phát hiện chu trình âm).

```java
// Biểu diễn đồ thị bằng adjacency list
Map<Integer, List<Integer>> graph = new HashMap<>();
graph.computeIfAbsent(0, k -> new ArrayList<>()).add(1);
```

## Code mẫu — Khung DFS và BFS

```java
static void dfs(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
    if (visited.contains(node)) return;
    visited.add(node);
    // xử lý node ở đây
    for (int neighbor : graph.getOrDefault(node, List.of())) {
        dfs(graph, neighbor, visited);
    }
}

static void bfs(Map<Integer, List<Integer>> graph, int start) {
    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited.add(start);
    while (!queue.isEmpty()) {
        int node = queue.poll();
        // xử lý node ở đây
        for (int neighbor : graph.getOrDefault(node, List.of())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                queue.offer(neighbor);
            }
        }
    }
}
```

## Bài tập

### Mức cơ bản

**Bài 9.1 — Số lượng thành phần liên thông (Number of Connected Components)**

Cho đồ thị vô hướng n node và danh sách cạnh, đếm số thành phần liên thông.

<details><summary>Gợi ý</summary>Duyệt từng node chưa thăm, dùng DFS/BFS để "quét" hết 1 thành phần liên thông, tăng biến đếm mỗi khi bắt đầu 1 lượt duyệt mới từ node chưa thăm.</details>

---

**Bài 9.2 — Kiểm tra đường đi giữa 2 node (Path Exists)**

<details><summary>Gợi ý</summary>BFS hoặc DFS từ node nguồn, kiểm tra có thăm được node đích không.</details>

---

**Bài 9.3 — Số đảo (Number of Islands)**

Cho lưới 2D gồm '1' (đất) và '0' (nước), đếm số đảo (các ô '1' liền kề theo 4 hướng tạo thành 1 đảo).

<details><summary>Gợi ý</summary>Duyệt từng ô, khi gặp '1' chưa thăm thì chạy DFS/BFS để "nhúng chìm" (đánh dấu đã thăm) toàn bộ đảo đó, tăng biến đếm.</details>

### Mức trung bình

**Bài 9.4 — Clone Graph**

Cho 1 node của đồ thị vô hướng liên thông, tạo bản sao (deep copy) toàn bộ đồ thị.

<details><summary>Gợi ý</summary>DFS/BFS kèm HashMap (node gốc → node clone) để tránh clone lại 1 node nhiều lần và xử lý đúng cấu trúc cạnh hai chiều.</details>

---

**Bài 9.5 — Course Schedule (Topological Sort / Phát hiện chu trình)**

Cho n khóa học và danh sách `[a, b]` nghĩa là phải học b trước a. Kiểm tra có thể học hết tất cả khóa học không (đồ thị có chu trình hay không).

<details><summary>Gợi ý</summary>Kahn's Algorithm: tính in-degree mỗi node, BFS bắt đầu từ các node in-degree = 0, giảm in-degree của hàng xóm khi xử lý xong 1 node. Nếu xử lý được tất cả node thì không có chu trình. Cách khác: DFS với 3 trạng thái (chưa thăm / đang thăm / đã xong) để phát hiện chu trình.</details>

---

**Bài 9.6 — Rotting Oranges (BFS đa nguồn)**

Lưới 2D với 0 (ô trống), 1 (cam tươi), 2 (cam thối). Mỗi phút, cam thối làm thối cam tươi liền kề. Tính số phút để toàn bộ cam thối hết (hoặc -1 nếu không thể).

<details><summary>Gợi ý</summary>BFS đa nguồn: đưa tất cả cam thối vào queue cùng lúc làm điểm bắt đầu (level 0), BFS theo từng "phút" (level), đếm số phút tới khi queue rỗng. Kiểm tra cuối cùng còn cam tươi nào sót lại không.</details>

### Mức nâng cao

**Bài 9.7 — Dijkstra's Algorithm (đường đi ngắn nhất có trọng số không âm)**

<details><summary>Gợi ý</summary>Dùng PriorityQueue (min-heap) lưu `[distance, node]`, luôn xử lý node có distance nhỏ nhất trước (giống BFS nhưng có trọng số). Cập nhật (relax) khoảng cách tới hàng xóm nếu tìm được đường ngắn hơn. Độ phức tạp: O((V+E) log V).</details>

---

**Bài 9.8 — Union-Find / Redundant Connection**

Cho đồ thị vô hướng ban đầu là cây, thêm 1 cạnh dư tạo thành chu trình. Tìm cạnh đó.

<details><summary>Gợi ý</summary>Tự cài Union-Find với path compression + union by rank. Duyệt từng cạnh theo thứ tự, nếu 2 đỉnh của cạnh đã cùng nhóm (find trả về cùng root) trước khi union thì đó là cạnh dư.</details>

---

**Bài 9.9 — Word Ladder (BFS tìm đường biến đổi từ ngắn nhất)**

Biến đổi từ `beginWord` thành `endWord`, mỗi lần đổi 1 ký tự, mỗi bước trung gian phải có trong `wordList`. Tìm độ dài đường biến đổi ngắn nhất.

<details><summary>Gợi ý</summary>Coi mỗi từ là 1 node, cạnh nối 2 từ chỉ khác nhau 1 ký tự. BFS từ `beginWord`. Để sinh hàng xóm hiệu quả, với mỗi từ thử thay từng vị trí bằng 26 ký tự và kiểm tra có trong wordList (dùng HashSet để tra cứu O(1)) không, thay vì so sánh từng cặp từ O(n²).</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Tự code được DFS và BFS từ đầu (cả đệ quy và lặp)
- [ ] Làm được Number of Islands và Course Schedule
- [ ] Hiểu Union-Find với path compression + union by rank
- [ ] Hiểu Dijkstra's Algorithm và khi nào không dùng được (có cạnh âm)
