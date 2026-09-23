# 7. Cây (Trees / BST) — Java

## Lý thuyết

- **Binary Tree**: mỗi node có tối đa 2 con (trái, phải).
- **Binary Search Tree (BST)**: con trái < node < con phải. Tìm kiếm/thêm/xóa trung bình O(log n), xấu nhất O(n) nếu cây lệch (skewed).
- **Cây cân bằng** (AVL, Red-Black Tree): tự cân bằng để đảm bảo O(log n) — Java's `TreeMap`/`TreeSet` dùng Red-Black Tree nội bộ.
- 3 kiểu duyệt DFS: **Preorder** (gốc-trái-phải), **Inorder** (trái-gốc-phải — cho BST sẽ ra thứ tự tăng dần), **Postorder** (trái-phải-gốc).
- **BFS / Level-order traversal**: duyệt theo từng tầng, dùng Queue.

```java
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}
```

## Code mẫu — Khung duyệt cây

```java
static void inorder(TreeNode root, List<Integer> result) {
    if (root == null) return;
    inorder(root.left, result);
    result.add(root.val);
    inorder(root.right, result);
}

static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            level.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(level);
    }
    return result;
}
```

## Bài tập

### Mức cơ bản

**Bài 7.1 — Tính chiều cao (độ sâu) của cây**

<details><summary>Gợi ý</summary>`maxDepth(root) = 1 + max(maxDepth(left), maxDepth(right))`, base case `root == null` trả về 0.</details>

---

**Bài 7.2 — Kiểm tra 2 cây có giống nhau (Same Tree)**

<details><summary>Gợi ý</summary>So sánh đệ quy: cùng null, cùng giá trị, và cây con trái/phải cũng giống nhau.</details>

---

**Bài 7.3 — Đảo cây nhị phân (Invert/Mirror Binary Tree)**

<details><summary>Gợi ý</summary>Đệ quy đảo cây con trái và phải, sau đó hoán đổi 2 con trỏ left/right.</details>

### Mức trung bình

**Bài 7.4 — Kiểm tra cây có phải BST hợp lệ (Validate BST)**

<details><summary>Gợi ý</summary>Không chỉ so sánh node với con trực tiếp — phải truyền theo khoảng giá trị hợp lệ (min, max) xuống mỗi node con.</details>

---

**Bài 7.5 — Tìm tổ tiên chung gần nhất (Lowest Common Ancestor — LCA) trong BST**

<details><summary>Gợi ý</summary>Tận dụng tính chất BST: nếu cả 2 node nhỏ hơn root thì LCA ở cây trái; nếu cả 2 lớn hơn thì ở cây phải; ngược lại root chính là LCA.</details>

---

**Bài 7.6 — Đường đi có tổng bằng target (Path Sum II)**

Tìm tất cả đường đi từ gốc đến lá có tổng giá trị bằng target.

<details><summary>Gợi ý</summary>DFS + backtracking, cộng dồn giá trị, khi tới lá kiểm tra tổng; nhớ "quay lui" (remove) khỏi list hiện tại khi rời khỏi node.</details>

### Mức nâng cao

**Bài 7.7 — Xây cây từ Preorder và Inorder traversal**

Input: `preorder=[3,9,20,15,7], inorder=[9,3,15,20,7]` → xây lại cây gốc.

<details><summary>Gợi ý</summary>Phần tử đầu của preorder luôn là root. Tìm vị trí root trong inorder để biết kích thước cây con trái/phải, đệ quy xây từng phần. Dùng HashMap để tìm vị trí trong inorder O(1) thay vì O(n) mỗi lần.</details>

---

**Bài 7.8 — Đường đi có tổng lớn nhất trong cây nhị phân (Binary Tree Maximum Path Sum)**

Đường đi có thể đi qua bất kỳ node nào, không bắt buộc qua gốc hoặc tới lá.

<details><summary>Gợi ý</summary>Với mỗi node, tính "gain" lớn nhất nếu đi 1 nhánh xuống (trái hoặc phải, không cả 2). Nhưng khi tính đáp án toàn cục tại node đó, được phép cộng cả 2 nhánh (left + right + node.val) vì đường đi "qua" node có thể rẽ tại đó.</details>

---

**Bài 7.9 — Serialize và Deserialize Binary Tree**

Chuyển cây thành chuỗi và khôi phục lại từ chuỗi đó.

<details><summary>Gợi ý</summary>Dùng preorder traversal, đánh dấu node null bằng ký tự đặc biệt (ví dụ "#"). Deserialize bằng cách đọc lần lượt token và đệ quy xây lại.</details>

---

**Bài 7.10 — Kth Smallest Element in a BST** (Medium)

Tìm phần tử nhỏ thứ k trong một Binary Search Tree.

Input: BST có inorder [1,2,3,4], k=2 -> Output: 2

<details><summary>Gợi ý</summary>Inorder traversal của BST cho các giá trị theo thứ tự tăng dần. Dừng khi đã thăm đủ k node.</details>

## Checklist trước khi qua chủ đề tiếp theo

- [ ] Phân biệt được Preorder/Inorder/Postorder/Level-order và biết khi nào dùng cái nào
- [ ] Tự làm được Validate BST và LCA
- [ ] Hiểu kỹ thuật DFS trả về "gain" để giải Binary Tree Maximum Path Sum
- [ ] Làm được Serialize/Deserialize Binary Tree
