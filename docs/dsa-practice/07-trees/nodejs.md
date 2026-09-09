# 7. Cây (Trees / BST) — Node.js (JavaScript)

## Lý thuyết

Khái niệm giống Java: Binary Tree, BST, các kiểu duyệt Preorder/Inorder/Postorder/Level-order. JS không có TreeNode built-in, tự định nghĩa bằng class.

```js
class TreeNode {
  constructor(val, left = null, right = null) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
```

## Code mẫu — Khung duyệt cây

```js
function inorder(root, result = []) {
  if (!root) return result;
  inorder(root.left, result);
  result.push(root.val);
  inorder(root.right, result);
  return result;
}

function levelOrder(root) {
  const result = [];
  if (!root) return result;
  const queue = [root];
  while (queue.length) {
    const size = queue.length;
    const level = [];
    for (let i = 0; i < size; i++) {
      const node = queue.shift();
      level.push(node.val);
      if (node.left) queue.push(node.left);
      if (node.right) queue.push(node.right);
    }
    result.push(level);
  }
  return result;
}
```

## Bài tập

### Mức cơ bản

**Bài 7.1 — Tính chiều cao (độ sâu) của cây**

<details><summary>Gợi ý</summary>`maxDepth(root) = 1 + max(maxDepth(left), maxDepth(right))`, base case là `null` trả về 0.</details>

---

**Bài 7.2 — Kiểm tra 2 cây có giống nhau (Same Tree)**

<details><summary>Gợi ý</summary>So sánh đệ quy: cùng null, cùng giá trị, và cây con trái/phải cũng giống nhau.</details>

---

**Bài 7.3 — Đảo cây nhị phân (Invert/Mirror Binary Tree)**

<details><summary>Gợi ý</summary>Đệ quy đảo cây con trái/phải, sau đó hoán đổi 2 thuộc tính left/right.</details>

### Mức trung bình

**Bài 7.4 — Kiểm tra cây có phải BST hợp lệ (Validate BST)**

<details><summary>Gợi ý</summary>Truyền khoảng giá trị hợp lệ (min, max) xuống mỗi node con, không chỉ so sánh với con trực tiếp.</details>

---

**Bài 7.5 — Tìm tổ tiên chung gần nhất (LCA) trong BST**

<details><summary>Gợi ý</summary>Tận dụng tính chất BST để xác định đi trái/phải/dừng tại root.</details>

---

**Bài 7.6 — Đường đi có tổng bằng target (Path Sum II)**

<details><summary>Gợi ý</summary>DFS + backtracking, cộng dồn giá trị, kiểm tra tổng tại lá, nhớ `path.pop()` khi quay lui.</details>

### Mức nâng cao

**Bài 7.7 — Xây cây từ Preorder và Inorder traversal**

<details><summary>Gợi ý</summary>Phần tử đầu preorder luôn là root. Dùng Map để tìm vị trí root trong inorder O(1), đệ quy xây cây con trái/phải.</details>

---

**Bài 7.8 — Đường đi có tổng lớn nhất trong cây nhị phân**

<details><summary>Gợi ý</summary>Hàm đệ quy trả về "gain" lớn nhất nếu đi 1 nhánh; cập nhật biến toàn cục bằng `node.val + leftGain + rightGain` (đường đi rẽ tại node).</details>

---

**Bài 7.9 — Serialize và Deserialize Binary Tree**

<details><summary>Gợi ý</summary>Preorder traversal, đánh dấu null bằng `'#'`. Deserialize đọc token tuần tự bằng con trỏ index hoặc queue.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Phân biệt được Preorder/Inorder/Postorder/Level-order
- [ ] Tự làm được Validate BST và LCA
- [ ] Hiểu kỹ thuật DFS trả về "gain" để giải Binary Tree Maximum Path Sum
- [ ] Làm được Serialize/Deserialize Binary Tree
