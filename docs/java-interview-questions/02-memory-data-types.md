# 2. Bộ nhớ & Kiểu dữ liệu

Đây là nhóm câu hỏi **lọc rất hiệu quả** giữa junior hiểu thật và junior học thuộc syntax, vì nó
đòi hỏi hiểu cơ chế bên dưới (under the hood) chứ không chỉ biết cách viết code.

## Câu 1: Stack và Heap khác nhau như thế nào? Biến nào lưu ở đâu?

### Đáp án chi tiết

**Stack memory:**
- Lưu các **biến local** (local variable) và **stack frame** của mỗi lời gọi method.
- Mỗi thread có **riêng một Stack** của mình (thread-safe tự nhiên, không cần đồng bộ).
- Lưu: biến kiểu primitive (`int`, `double`, `boolean`...) được khai báo local, và **reference**
  (địa chỉ tham chiếu) tới object nằm trên Heap.
- Khi method kết thúc, stack frame của method đó bị pop ra khỏi stack → biến local bị giải phóng
  ngay lập tức (tốc độ truy cập rất nhanh, theo kiểu LIFO).
- Nếu gọi đệ quy quá sâu → `StackOverflowError`.

**Heap memory:**
- Lưu tất cả **object** (instance của class) và **array**, bất kể được tạo ra ở đâu.
- Heap được **chia sẻ giữa tất cả thread** trong JVM (vì vậy object có thể bị truy cập đồng thời
  từ nhiều thread → cần đồng bộ hóa nếu mutable).
- Object trên Heap được giải phóng bởi **Garbage Collector**, không giải phóng ngay khi method
  kết thúc — chỉ giải phóng khi không còn reference nào trỏ tới nó.
- Nếu tạo quá nhiều object không được giải phóng → `OutOfMemoryError: Java heap space`.

```java
public void example() {
    int x = 10;                  // x: lưu trên Stack (primitive)
    String s = new String("hi"); // s: reference lưu trên Stack, object "hi" lưu trên Heap
    Person p = new Person();     // p: reference trên Stack, Person object trên Heap
}
```

Hình dung: Stack giống "sổ ghi chú tạm" của từng lời gọi hàm, Heap giống "nhà kho chung" chứa toàn
bộ object thực sự — Stack chỉ giữ "địa chỉ" để biết object nằm ở đâu trong Heap.

### Follow-up: Khi truyền object vào method, Java truyền theo giá trị hay tham chiếu?

Đây là câu hỏi kinh điển khiến rất nhiều junior (và cả senior) trả lời sai. Câu trả lời chính xác:

**Java luôn truyền theo giá trị (pass-by-value) — nhưng giá trị đó, đối với object, là giá trị
của reference (địa chỉ).**

```java
public void modify(Person p) {
    p.setName("Changed");  // thay đổi field của object mà p đang trỏ tới -> ẢNH HƯỞNG ra ngoài
    p = new Person("New"); // gán p trỏ tới object MỚI -> KHÔNG ảnh hưởng ra ngoài
}

public static void main(String[] args) {
    Person person = new Person("Original");
    modify(person);
    System.out.println(person.getName()); // In ra "Changed", KHÔNG phải "New"
}
```

Giải thích: khi gọi `modify(person)`, một **bản sao của reference** (không phải bản sao object)
được truyền vào tham số `p`. Cả `person` (bên ngoài) và `p` (bên trong method) đang **trỏ tới cùng
một object** trên Heap → thay đổi field qua `p` sẽ thấy được từ `person`. Nhưng nếu gán lại
`p = new Person(...)`, ta chỉ đổi hướng trỏ của biến cục bộ `p`, không ảnh hưởng đến `person` ở
ngoài vì `person` vẫn giữ reference cũ.

---

## Câu 2: Phân biệt Primitive type và Wrapper class. Autoboxing/Unboxing là gì?

### Đáp án chi tiết

| Tiêu chí | Primitive (`int`, `double`, `boolean`...) | Wrapper class (`Integer`, `Double`, `Boolean`...) |
|----------|---------|---------|
| Bản chất | Kiểu dữ liệu nguyên thủy, lưu trực tiếp giá trị | Object, lưu reference tới object trên Heap chứa giá trị |
| Giá trị mặc định | `0`, `0.0`, `false`... | `null` |
| Dùng trong Collection (`List<Integer>`) | **Không thể** dùng trực tiếp (Generics không hỗ trợ primitive) | Phải dùng Wrapper class |
| Hiệu năng | Nhanh hơn, không tốn overhead object | Chậm hơn (overhead tạo object, boxing/unboxing) |
| Có thể `null` | Không | Có |

**Autoboxing**: tự động chuyển từ primitive → wrapper class.
**Unboxing**: tự động chuyển từ wrapper class → primitive.

```java
Integer boxed = 100;        // autoboxing: int 100 -> Integer.valueOf(100)
int unboxed = boxed;        // unboxing: boxed.intValue()

List<Integer> list = new ArrayList<>();
list.add(5); // 5 (int) tự động được autobox thành Integer
```

### Câu hỏi "trap" kinh điển — bài kiểm tra hiểu sâu nhất của mục này

```java
Integer a = 127;
Integer b = 127;
System.out.println(a == b); // ?

Integer c = 200;
Integer d = 200;
System.out.println(c == d); // ?
```

**Kết quả:** `a == b` là `true`, nhưng `c == d` là `false`.

**Giải thích:** Java có cơ chế **Integer Cache** (`IntegerCache`, định nghĩa trong
`java.lang.Integer`), tự động cache sẵn các object `Integer` cho giá trị từ **-128 đến 127**
(`autoboxing` qua `Integer.valueOf()` sẽ trả về object cache có sẵn trong khoảng này, thay vì tạo
object mới). Vì vậy:
- `Integer a = 127` và `Integer b = 127` đều trỏ tới **cùng một object cache** → `a == b` so sánh
  reference → `true`.
- `Integer c = 200` và `Integer d = 200` nằm ngoài khoảng cache → mỗi lần autobox sẽ tạo ra
  **object mới** → `c == d` so sánh hai reference khác nhau → `false`.

**Bài học rút ra (điều junior PHẢI hiểu)**: không bao giờ dùng `==` để so sánh giá trị của Wrapper
class hoặc object — luôn dùng `.equals()`. `==` chỉ nên dùng cho primitive, hoặc khi cố ý kiểm tra
hai reference có là cùng một object hay không.

```java
Integer c = 200, d = 200;
System.out.println(c.equals(d)); // true — đúng cách so sánh giá trị
```

---

## Câu 3: `==` và `.equals()` khác nhau như thế nào?

### Đáp án chi tiết

- **`==`**: với primitive, so sánh **giá trị**. Với object (bao gồm String, Wrapper class), so
  sánh **địa chỉ reference** (hai biến có trỏ tới cùng một object trên Heap hay không).
- **`.equals()`**: là method định nghĩa trong class `Object`, mặc định cũng so sánh reference
  (giống `==`) — *nhưng* nhiều class (`String`, `Integer`, các class trong Collection Framework...)
  đã **override `.equals()`** để so sánh theo **nội dung/giá trị logic**.

```java
String s1 = new String("hello");
String s2 = new String("hello");
System.out.println(s1 == s2);       // false -> 2 object khác nhau trên Heap
System.out.println(s1.equals(s2));  // true  -> String đã override equals() để so sánh nội dung

String s3 = "hello";
String s4 = "hello";
System.out.println(s3 == s4);       // true -> cùng trỏ tới 1 object trong String Pool
```

---

## Câu 4: String Pool là gì? Vì sao String là immutable?

### Đáp án chi tiết

**String Pool (String Constant Pool)** là một vùng nhớ đặc biệt trong Heap (từ Java 7+, trước đó
nằm trong PermGen/Method Area), dùng để lưu các String literal nhằm **tái sử dụng** — tránh tạo
nhiều object String trùng nội dung không cần thiết, tiết kiệm bộ nhớ.

```java
String s1 = "hello";       // literal -> JVM kiểm tra String Pool, nếu có "hello" thì trả về reference đó
String s2 = "hello";       // trùng nội dung -> trả về CÙNG reference với s1
String s3 = new String("hello"); // dùng `new` -> ép tạo object MỚI trên Heap (ngoài Pool), không tái sử dụng

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false

String s4 = s3.intern(); // .intern() đưa nội dung vào Pool (hoặc lấy ra nếu đã có) và trả về reference đó
System.out.println(s1 == s4); // true
```

**Vì sao String là immutable (không thể thay đổi nội dung sau khi tạo)?**

1. **An toàn cho String Pool**: nếu String có thể thay đổi, việc `s1` và `s2` cùng trỏ tới một
   object trong Pool sẽ rất nguy hiểm — thay đổi `s1` sẽ vô tình làm `s2` thay đổi theo (vì chúng
   share cùng object).
2. **Thread-safe tự nhiên**: object immutable có thể được chia sẻ giữa nhiều thread mà không cần
   đồng bộ hóa (synchronization), vì không có thao tác nào làm thay đổi state của nó.
3. **An toàn khi dùng làm key cho `HashMap`/`HashSet`**: nếu String có thể đổi nội dung sau khi đã
   được dùng làm key, `hashCode()` của nó sẽ thay đổi → key bị "lạc" trong bucket cũ của HashMap,
   không tìm lại được → phá vỡ tính đúng đắn của cấu trúc dữ liệu.
4. **An toàn về bảo mật**: ví dụ String chứa username/URL/file path được dùng trong nhiều lớp xử
   lý (class loader, network connection...) — nếu có thể thay đổi "ngầm" sau khi được validate ở
   một nơi, có thể dẫn tới lỗ hổng bảo mật (time-of-check to time-of-use).

```java
String s = "hello";
s.concat(" world");       // tạo ra object MỚI "hello world", không thay đổi object "hello" cũ
System.out.println(s);    // vẫn in "hello" — vì concat() không thay đổi s, chỉ trả về object mới
s = s.concat(" world");   // phải gán lại mới nhận được giá trị mới
System.out.println(s);    // "hello world"
```

---

## Câu 5: String, StringBuilder, StringBuffer khác nhau ra sao? Khi nào dùng cái nào?

### Đáp án chi tiết

| Tiêu chí | String | StringBuilder | StringBuffer |
|----------|--------|----------------|--------------|
| Mutable? | Immutable | Mutable | Mutable |
| Thread-safe? | Có (vì immutable) | **Không** | **Có** (các method được `synchronized`) |
| Hiệu năng | Chậm khi nối chuỗi nhiều lần (mỗi lần tạo object mới) | Nhanh nhất (không đồng bộ hóa) | Chậm hơn StringBuilder (overhead đồng bộ hóa) |
| Khi nào dùng | Chuỗi cố định, ít thay đổi | Nối chuỗi nhiều lần trong **single-thread** (ví dụ vòng lặp build chuỗi) | Nối chuỗi nhiều lần khi **nhiều thread** cùng truy cập một StringBuffer |

```java
// Ví dụ minh chứng vì sao dùng String để nối chuỗi trong loop là tệ
String result = "";
for (int i = 0; i < 10000; i++) {
    result += i; // MỖI lần += tạo ra một object String MỚI -> rất tốn bộ nhớ và CPU (O(n^2))
}

// Cách đúng:
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 10000; i++) {
    sb.append(i); // thay đổi trực tiếp buffer nội bộ, không tạo object mới mỗi lần -> O(n)
}
String result2 = sb.toString();
```

### Follow-up: Vì sao `String result += i` trong loop bị coi là anti-pattern?

Vì mỗi lần `+=`, do String immutable, JVM phải: tạo một object String mới, copy toàn bộ nội dung
cũ + nội dung mới vào object đó, rồi gán reference `result` sang object mới này. Object cũ trở
thành "rác" chờ Garbage Collector dọn. Lặp lại N lần → độ phức tạp tổng thể là O(n²) thay vì
O(n) như khi dùng `StringBuilder`.

---

## Gợi ý câu hỏi tình huống bổ sung

1. Đưa đoạn code có `Integer` so sánh bằng `==` ngoài khoảng cache (như ví dụ Câu 2) và hỏi output
   — đây là câu lọc rất hiệu quả.
2. Hỏi: "`final` với primitive và `final` với object khác nhau thế nào?" → Đáp: `final` trên
   primitive làm giá trị không đổi; `final` trên reference object chỉ làm **reference** không đổi
   (không thể trỏ sang object khác), nhưng **nội dung bên trong object đó vẫn có thể thay đổi**
   nếu object đó là mutable.
   ```java
   final List<String> list = new ArrayList<>();
   list.add("a"); // OK — không đổi reference, chỉ đổi nội dung object
   // list = new ArrayList<>(); // LỖI compile — không được gán lại reference
   ```
