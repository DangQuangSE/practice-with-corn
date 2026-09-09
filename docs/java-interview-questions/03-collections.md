# 3. Collection Framework

## Câu 1: List, Set, Map khác nhau như thế nào?

### Đáp án chi tiết

| Tiêu chí | List | Set | Map |
|----------|------|-----|-----|
| Cho phép trùng lặp? | Có | Không | Key không trùng, Value có thể trùng |
| Có thứ tự (index)? | Có (truy cập theo index) | Tùy implementation (`LinkedHashSet` giữ thứ tự thêm, `TreeSet` sắp xếp, `HashSet` không đảm bảo thứ tự) | Tùy implementation tương tự Set (vì Map thường implement dựa trên cấu trúc tương tự Set cho key) |
| Lưu trữ | Một danh sách phần tử | Một tập hợp phần tử duy nhất | Cặp key-value |
| Implementation phổ biến | `ArrayList`, `LinkedList` | `HashSet`, `LinkedHashSet`, `TreeSet` | `HashMap`, `LinkedHashMap`, `TreeMap` |

```java
List<String> list = new ArrayList<>();
list.add("a");
list.add("a"); // OK, được phép trùng -> list: ["a", "a"]

Set<String> set = new HashSet<>();
set.add("a");
set.add("a"); // bị bỏ qua, không thêm trùng -> set: ["a"]

Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("a", 2); // ghi đè giá trị cũ -> map: {"a": 2}
```

---

## Câu 2: ArrayList và LinkedList khác nhau ra sao? Khi nào dùng cái nào?

### Đáp án chi tiết

**ArrayList**: bên dưới dùng **array động** (dynamic array). Khi array đầy, JVM tạo array mới có
kích thước lớn hơn (thường gấp 1.5x) và copy toàn bộ phần tử cũ sang.

**LinkedList**: bên dưới dùng **doubly linked list** — mỗi node giữ con trỏ tới node trước và
node sau.

| Thao tác | ArrayList | LinkedList |
|----------|-----------|------------|
| `get(index)` | O(1) — truy cập trực tiếp theo index (random access) | O(n) — phải duyệt từ đầu hoặc cuối tới node cần tìm |
| `add(element)` ở cuối | O(1) amortized (đôi khi O(n) khi phải resize array) | O(1) |
| `add(index, element)` ở giữa | O(n) — phải dời (shift) các phần tử phía sau | O(n) để tìm tới vị trí, nhưng O(1) để chèn sau khi đã tìm thấy node |
| `remove(index)` | O(n) — phải dời các phần tử | O(n) để tìm, O(1) để xóa node |
| Bộ nhớ | Tiết kiệm hơn (chỉ lưu data) | Tốn hơn (mỗi node lưu thêm 2 con trỏ prev/next) |

**Khi nào dùng:**
- `ArrayList`: khi cần **truy cập ngẫu nhiên** nhiều (`get(i)` thường xuyên), ít insert/delete ở
  giữa danh sách. **Đây là lựa chọn mặc định trong hầu hết trường hợp thực tế.**
- `LinkedList`: khi cần insert/delete **nhiều ở đầu hoặc giữa** danh sách, ít truy cập theo index.
  Trong thực tế hiện đại, `LinkedList` ít được dùng vì overhead bộ nhớ cao và cache locality kém
  (các node nằm rải rác trong heap, không liên tục như array → chậm hơn dự kiến trên CPU cache).

---

## Câu 3: HashMap hoạt động bên trong như thế nào?

### Đáp án chi tiết (đây là câu hỏi lọc rất mạnh — senior thường dùng để phân biệt junior khá và junior giỏi)

**Cấu trúc dữ liệu nền:** `HashMap` bên trong là một **array of buckets** (`Node<K,V>[] table`).
Mỗi bucket chứa một **linked list** (hoặc **red-black tree** nếu bucket có quá nhiều phần tử, từ
Java 8+) các entry (`key`, `value`, `hash`, con trỏ `next`).

**Quy trình `put(key, value)`:**
1. Tính `hash(key)` — gọi `key.hashCode()`, sau đó áp dụng thêm một bước "xáo trộn bit"
   (`hash ^ (hash >>> 16)`) để giảm collision khi nhiều hashCode có pattern bit giống nhau.
2. Tính vị trí bucket: `index = hash & (table.length - 1)` (tương đương `hash % table.length` khi
   table.length là lũy thừa của 2 — đây là lý do HashMap luôn dùng capacity là lũy thừa của 2,
   vì phép `&` nhanh hơn `%`).
3. Nếu bucket tại `index` rỗng → tạo node mới đặt vào đó.
4. Nếu bucket đã có entry (**collision** — nhiều key khác nhau cùng rơi vào 1 bucket):
   - Duyệt qua linked list trong bucket, so sánh `hash` và `.equals(key)` với từng entry.
   - Nếu tìm thấy key đã tồn tại (hash giống và `equals()` trả `true`) → ghi đè value.
   - Nếu không tìm thấy → thêm entry mới vào cuối linked list (hoặc vào tree nếu đã chuyển dạng
     tree).
5. Nếu số lượng entry trong một bucket vượt quá `TREEIFY_THRESHOLD` (= 8) **và** tổng table size
   ≥ 64 → bucket đó được chuyển từ linked list sang **red-black tree** để tăng tốc tra cứu từ
   O(n) lên O(log n) trong trường hợp nhiều collision (cải tiến quan trọng từ Java 8).
6. Nếu tổng số entry trong map vượt quá `threshold` (= `capacity * loadFactor`, mặc định
   `loadFactor = 0.75`) → **resize** (tăng gấp đôi capacity) và **rehash** toàn bộ entry sang
   table mới.

**Quy trình `get(key)`:** tính `hash(key)` → tìm đúng bucket → duyệt linked list/tree trong bucket,
so sánh `hash` rồi `.equals()` để tìm entry khớp.

```java
Map<String, Integer> map = new HashMap<>();
map.put("apple", 1);
map.put("banana", 2);
// "apple".hashCode() và "banana".hashCode() có thể map vào cùng 1 bucket (collision)
// -> cả 2 entry nằm trong cùng linked list của bucket đó, phân biệt nhau bằng equals()
```

### Follow-up: Vì sao phải override cả `hashCode()` và `equals()` cùng lúc?

Đây là một trong những câu hỏi **quan trọng nhất** để kiểm tra hiểu sâu, vì rất nhiều bug thực tế
xuất phát từ việc chỉ override 1 trong 2.

**Hợp đồng (contract) giữa hashCode() và equals():**
1. Nếu `a.equals(b)` trả về `true`, thì **bắt buộc** `a.hashCode() == b.hashCode()`.
2. Nếu `a.hashCode() == b.hashCode()`, **không bắt buộc** `a.equals(b)` phải `true` (đây gọi là
   hash collision, là điều bình thường, HashMap đã xử lý bằng cách so sánh `equals()` thêm).
3. `hashCode()` phải **nhất quán** — gọi nhiều lần trên cùng object (state không đổi) phải luôn
   trả về kết quả giống nhau.

**Bug thực tế nếu chỉ override `equals()` mà không override `hashCode()`:**

```java
public class Employee {
    private String id;

    public Employee(String id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Employee)) return false;
        return this.id.equals(((Employee) o).id);
    }
    // QUÊN override hashCode() -> dùng hashCode() mặc định của Object (dựa vào địa chỉ memory)
}

Set<Employee> set = new HashSet<>();
Employee e1 = new Employee("E001");
set.add(e1);

Employee e2 = new Employee("E001"); // logic giống e1 (cùng id), equals() trả true
System.out.println(e1.equals(e2));     // true
System.out.println(set.contains(e2));  // FALSE! -- Bug!
```

**Giải thích bug:** `HashSet`/`HashMap` dùng `hashCode()` để xác định bucket cần tìm **trước khi**
gọi `equals()` để so sánh. Vì `e1` và `e2` không override `hashCode()`, mỗi object có `hashCode()`
khác nhau (dựa vào địa chỉ bộ nhớ) → `e2` bị tính rơi vào **bucket khác** với `e1` → `HashSet`
không bao giờ tìm tới đúng bucket chứa `e1` để gọi `equals()` so sánh → `contains()` trả `false`
mặc dù về logic `e1.equals(e2)` là `true`. Đây là lỗi điển hình khi không tuân thủ hợp đồng
hashCode-equals.

**Cách sửa đúng:**

```java
@Override
public int hashCode() {
    return Objects.hash(id); // hoặc id.hashCode()
}
```

**Quy tắc thực hành**: trong IDE hiện đại (IntelliJ, Eclipse) hoặc dùng Lombok `@EqualsAndHashCode`,
luôn generate cả 2 method cùng lúc, dựa trên cùng tập field, để tránh vi phạm hợp đồng này.

---

## Câu 4: Fail-fast và Fail-safe Iterator khác nhau ra sao?

### Đáp án chi tiết

**Fail-fast**: Iterator của các Collection thông thường (`ArrayList`, `HashMap`, `HashSet`...) sẽ
**throw `ConcurrentModificationException`** ngay lập tức nếu Collection bị thay đổi cấu trúc
(add/remove phần tử) trong khi đang duyệt bằng iterator (trừ khi thay đổi đó được thực hiện qua
chính iterator đó, ví dụ `iterator.remove()`).

Cơ chế: các Collection này có một biến đếm nội bộ gọi là `modCount`. Mỗi lần Collection bị thay
đổi cấu trúc, `modCount` tăng lên. Iterator lưu lại `expectedModCount` lúc khởi tạo; mỗi lần gọi
`next()`, nó so sánh `modCount` hiện tại với `expectedModCount` — nếu khác nhau, throw exception
ngay (không đợi đến lúc lỗi thực sự xảy ra).

```java
List<String> list = new ArrayList<>(List.of("a", "b", "c"));
for (String s : list) {
    if (s.equals("b")) {
        list.remove(s); // ConcurrentModificationException! -- thay đổi list trong khi duyệt for-each
    }
}
```

**Cách sửa đúng** — dùng `Iterator.remove()` hoặc `removeIf()`:

```java
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    String s = it.next();
    if (s.equals("b")) {
        it.remove(); // an toàn -- cập nhật expectedModCount đồng bộ
    }
}
// hoặc đơn giản hơn:
list.removeIf(s -> s.equals("b"));
```

**Fail-safe**: các Collection trong package `java.util.concurrent` (`ConcurrentHashMap`,
`CopyOnWriteArrayList`...) **không throw exception** khi bị thay đổi trong lúc duyệt, vì chúng
hoạt động trên một **bản sao (snapshot)** của dữ liệu tại thời điểm tạo iterator
(`CopyOnWriteArrayList`), hoặc cho phép đọc dữ liệu cũ một cách an toàn trong khi phần khác của
map đang được cập nhật (`ConcurrentHashMap`, dùng cơ chế đồng bộ ở mức bucket/segment thay vì
khóa toàn bộ map). Đánh đổi: tốn thêm bộ nhớ/thời gian để copy hoặc quản lý đồng bộ phức tạp hơn.

---

## Gợi ý câu hỏi tình huống bổ sung

1. "Nếu dùng một object **mutable** (object mà field có thể thay đổi sau khi tạo) làm key cho
   `HashMap`, điều gì có thể xảy ra?" → Đáp: nếu thay đổi field của key sau khi đã `put()` vào map
   (làm `hashCode()` thay đổi), bucket cũ vẫn giữ entry đó, nhưng khi `get()` lại bằng key (đã đổi
   giá trị), hash mới sẽ trỏ tới bucket khác → không tìm thấy entry → "lạc mất" dữ liệu trong map
   mặc dù entry vẫn tồn tại vật lý. **Bài học: nên dùng object immutable làm key cho HashMap.**
2. "`HashMap` và `Hashtable` khác nhau ra sao?" → `Hashtable` là class cũ (legacy, thuộc Java 1.0),
   các method được `synchronized` toàn bộ (thread-safe nhưng chậm), không cho phép `null` key/value.
   `HashMap` không thread-safe nhưng nhanh hơn, cho phép 1 `null` key và nhiều `null` value. Trong
   môi trường đa luồng, nên dùng `ConcurrentHashMap` thay vì `Hashtable`.
