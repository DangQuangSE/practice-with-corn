# 7. Java 8+ Features

Đây là nhóm câu hỏi gần như **bắt buộc** với mặt bằng tuyển dụng hiện tại, vì code Java hiện đại
(đặc biệt với Spring Boot) sử dụng rất nhiều Stream API, Lambda, Optional.

## Câu 1: Lambda Expression và Functional Interface là gì?

### Đáp án chi tiết

**Functional Interface** là một interface chỉ có **đúng 1 abstract method** (có thể có thêm
`default`/`static` method, không tính vào số lượng abstract method). Annotation `@FunctionalInterface`
là tùy chọn (optional) nhưng nên dùng để compiler kiểm tra giúp, tránh vô tình thêm method thứ 2
làm vi phạm điều kiện.

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b); // chỉ 1 abstract method
}
```

**Lambda Expression** là cách viết **ngắn gọn** để tạo ra một implementation của Functional
Interface, thay cho việc viết hẳn một anonymous class dài dòng.

```java
// Cách cũ (anonymous class) trước Java 8:
Calculator addOld = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};

// Cách mới (lambda) từ Java 8:
Calculator add = (a, b) -> a + b;

System.out.println(add.calculate(3, 4)); // 7
```

**Các Functional Interface có sẵn quan trọng trong `java.util.function`:**

| Interface | Signature | Mục đích |
|-----------|-----------|----------|
| `Function<T, R>` | `R apply(T t)` | Nhận vào 1 giá trị, trả về 1 giá trị (khác kiểu) |
| `Predicate<T>` | `boolean test(T t)` | Nhận vào 1 giá trị, trả về `true/false` (dùng để filter, điều kiện) |
| `Consumer<T>` | `void accept(T t)` | Nhận vào 1 giá trị, không trả về gì (dùng để thực hiện hành động) |
| `Supplier<T>` | `T get()` | Không nhận gì, trả về 1 giá trị (dùng để "cung cấp" giá trị, ví dụ lazy initialization) |

```java
Predicate<Integer> isEven = n -> n % 2 == 0;
System.out.println(isEven.test(4)); // true

Function<String, Integer> stringLength = s -> s.length();
System.out.println(stringLength.apply("hello")); // 5

Consumer<String> printer = s -> System.out.println("Value: " + s);
printer.accept("test"); // In ra "Value: test"
```

### Follow-up: Method Reference (`::`) là gì?

Là cách viết ngắn gọn hơn nữa cho lambda khi lambda chỉ đơn giản là **gọi lại một method đã tồn
tại sẵn**.

```java
List<String> names = List.of("Charlie", "Alice", "Bob");

names.forEach(s -> System.out.println(s)); // lambda thông thường
names.forEach(System.out::println);         // method reference -- tương đương, ngắn gọn hơn

names.sort((a, b) -> a.compareTo(b));        // lambda
names.sort(String::compareTo);                // method reference
```

---

## Câu 2: Stream API là gì? Viết một đoạn code transform List bằng Stream.

### Đáp án chi tiết

**Stream API** cho phép xử lý dữ liệu theo phong cách **khai báo (declarative)** và
**functional**, thay cho việc viết loop thủ công (imperative). Stream **không lưu trữ dữ liệu**
— nó chỉ là một "đường ống xử lý" (pipeline) áp dụng lên một nguồn dữ liệu (Collection, array...).

**Đặc điểm quan trọng của Stream:**
- **Lazy evaluation**: các operation trung gian (`map`, `filter`...) gọi là **intermediate
  operation**, chúng **không thực thi ngay** — chỉ khi gặp một **terminal operation**
  (`collect`, `forEach`, `count`, `reduce`...) thì toàn bộ pipeline mới thực sự chạy.
- **Chỉ dùng được 1 lần**: một Stream sau khi đã có terminal operation thì không thể tái sử dụng
  lại — phải tạo Stream mới từ nguồn dữ liệu.
- **Không thay đổi nguồn dữ liệu gốc** — Stream tạo ra dữ liệu mới (immutable theo nghĩa không
  sửa list ban đầu, trừ khi tự ý gọi side-effect bên trong lambda, điều này không được khuyến
  khích).

```java
List<String> names = List.of("Charlie", "Alice", "Bob", "David", "Eve");

// Yêu cầu: lấy ra danh sách tên có độ dài > 3, viết HOA, sắp xếp theo alphabet
List<String> result = names.stream()           // tạo Stream từ List
    .filter(name -> name.length() > 3)          // intermediate: giữ lại tên dài hơn 3 ký tự
    .map(String::toUpperCase)                    // intermediate: chuyển thành chữ hoa
    .sorted()                                     // intermediate: sắp xếp alphabet
    .collect(Collectors.toList());               // terminal: thu thập kết quả thành List

System.out.println(result); // [ALICE, CHARLIE, DAVID]
```

**Một số ví dụ Stream thông dụng khác:**

```java
// Tính tổng độ dài tất cả tên
int totalLength = names.stream()
    .mapToInt(String::length)
    .sum();

// Group theo độ dài tên
Map<Integer, List<String>> grouped = names.stream()
    .collect(Collectors.groupingBy(String::length));

// Kiểm tra có tên nào bắt đầu bằng "A" không
boolean hasA = names.stream()
    .anyMatch(name -> name.startsWith("A"));

// Tìm tên dài nhất
Optional<String> longest = names.stream()
    .max(Comparator.comparingInt(String::length));
```

### Follow-up: `map()` và `filter()` khác nhau ra sao?

- `map(Function)`: **biến đổi** mỗi phần tử thành một phần tử khác (có thể khác kiểu dữ liệu),
  số lượng phần tử **không đổi**.
- `filter(Predicate)`: **giữ lại** những phần tử thỏa điều kiện, số lượng phần tử có thể **giảm
  đi**, kiểu dữ liệu không đổi.

### Follow-up: Stream tuần tự (`stream()`) và song song (`parallelStream()`) khác nhau ra sao?

`parallelStream()` chia dữ liệu ra xử lý trên **nhiều thread** (dùng chung Fork/Join pool mặc
định của JVM), có thể nhanh hơn với dữ liệu lớn và xử lý nặng — nhưng có overhead chia/gộp dữ
liệu, nên với dữ liệu nhỏ hoặc xử lý đơn giản, `parallelStream()` có thể **chậm hơn** `stream()`
thông thường. Cũng cần cẩn trọng nếu lambda bên trong có side-effect không thread-safe.

---

## Câu 3: `Optional` là gì? Dùng để giải quyết vấn đề gì?

### Đáp án chi tiết

`Optional<T>` là một **wrapper class** đại diện cho một giá trị **có thể có hoặc không có**
(present hoặc empty), được thiết kế để **giảm thiểu `NullPointerException`** và buộc người gọi
phải **chủ động xử lý trường hợp giá trị không tồn tại** một cách rõ ràng, thay vì âm thầm trả về
`null` rồi quên kiểm tra.

```java
// Cách cũ -- dễ quên check null, dẫn tới NullPointerException
public String findEmailById(String id) {
    User user = userRepository.findById(id); // có thể trả về null
    return user.getEmail(); // NPE nếu user == null và caller quên check
}

// Cách dùng Optional -- buộc caller phải xử lý rõ ràng trường hợp rỗng
public Optional<User> findById(String id) {
    User user = userRepository.findRaw(id);
    return Optional.ofNullable(user); // bọc lại, có thể là Optional.empty() nếu user null
}

// Sử dụng:
Optional<User> userOpt = findById("123");

String email = userOpt
    .map(User::getEmail)              // chỉ áp dụng map nếu Optional có giá trị
    .orElse("no-email@default.com");  // giá trị mặc định nếu Optional rỗng

userOpt.ifPresent(user -> System.out.println("Found: " + user.getName()));

User user = userOpt.orElseThrow(() -> new UserNotFoundException("Không tìm thấy user"));
```

**Các method quan trọng của `Optional`:**

| Method | Mục đích |
|--------|----------|
| `Optional.of(value)` | Tạo Optional có giá trị (throw `NullPointerException` nếu `value` là `null`) |
| `Optional.ofNullable(value)` | Tạo Optional, tự động thành `empty()` nếu `value` là `null` (an toàn hơn `of`) |
| `Optional.empty()` | Tạo Optional rỗng |
| `isPresent()` / `isEmpty()` | Kiểm tra có giá trị hay không |
| `get()` | Lấy giá trị, **throw `NoSuchElementException` nếu rỗng** — nên tránh dùng trực tiếp, ưu tiên `orElse`/`orElseThrow` |
| `orElse(default)` | Lấy giá trị, hoặc trả về giá trị mặc định nếu rỗng |
| `orElseThrow(supplier)` | Lấy giá trị, hoặc throw exception tùy chỉnh nếu rỗng |
| `map(Function)` | Biến đổi giá trị bên trong (nếu có), giữ nguyên `empty()` nếu rỗng |
| `ifPresent(Consumer)` | Thực hiện hành động chỉ khi có giá trị |

### Follow-up: Vì sao không nên dùng `Optional` làm kiểu của field trong class hoặc tham số method?

Đây là một best practice ít junior biết: `Optional` được thiết kế chủ yếu để làm **kiểu trả về**
(return type) của method, giúp caller biết rõ "method này có thể không trả về gì". Không nên dùng
làm:
- **Field của class**: vì `Optional` không implement `Serializable`, và làm tăng độ phức tạp khi
  serialize/deserialize object (ví dụ qua JPA/Hibernate, Jackson JSON).
- **Tham số của method**: vì caller có thể truyền `null` cho chính `Optional` đó
  (`method(null)`), khiến vấn đề NPE quay lại nguyên vẹn — nếu cần tham số tùy chọn, nên dùng
  overload method hoặc giá trị mặc định, không nên bọc bằng `Optional`.

---

## Gợi ý câu hỏi tình huống bổ sung

1. Đưa đoạn code Stream có lỗi logic nhỏ (ví dụ quên `collect()`, chỉ gọi `map()` rồi không dùng
   kết quả) và hỏi "dòng này có thực sự chạy không, tại sao?" → Kiểm tra hiểu về lazy evaluation.
2. "Có thể gọi 2 terminal operation trên cùng 1 Stream object không?" → **Không**, sẽ throw
   `IllegalStateException: stream has already been operated upon or closed`. Phải tạo Stream mới
   từ nguồn dữ liệu nếu cần xử lý lại.
3. "`Comparator` kết hợp với lambda như thế nào để sort theo nhiều tiêu chí?"
   ```java
   list.sort(Comparator.comparing(Person::getAge)
                       .thenComparing(Person::getName));
   ```
