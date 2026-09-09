# 4. Exception Handling

## Câu 1: Checked Exception và Unchecked Exception khác nhau ra sao?

### Đáp án chi tiết

**Cây phân cấp Exception trong Java:**

```
Throwable
├── Error                     (lỗi nghiêm trọng ở tầng JVM, không nên tự catch/xử lý)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── Checked Exception     (mọi class kế thừa Exception, TRỪ RuntimeException và con của nó)
    │   ├── IOException
    │   ├── SQLException
    │   └── ClassNotFoundException
    └── RuntimeException      (= Unchecked Exception)
        ├── NullPointerException
        ├── ArrayIndexOutOfBoundsException
        ├── IllegalArgumentException
        ├── ClassCastException
        └── ArithmeticException
```

| Tiêu chí | Checked Exception | Unchecked Exception (RuntimeException) |
|----------|--------------------|------------------------------------------|
| Kiểm tra ở compile-time? | **Có** — compiler bắt buộc phải `try-catch` hoặc khai báo `throws` | Không — compiler không bắt buộc |
| Khi nào xảy ra | Các lỗi có thể **dự đoán và phục hồi được** từ môi trường bên ngoài (file không tồn tại, mất kết nối network, database lỗi...) | Các lỗi thường do **bug logic trong code** (truy cập index sai, chia 0, gọi method trên null...) |
| Ví dụ | `IOException`, `SQLException` | `NullPointerException`, `ArithmeticException` |
| Triết lý thiết kế | Buộc developer phải **chủ động xử lý** vì lỗi này có thể xảy ra dù code đúng logic (ví dụ file bị xóa giữa đường) | Không buộc xử lý vì những lỗi này **lẽ ra không nên xảy ra** nếu code được viết đúng — mục đích là để lộ ra bug sớm, không nên "che" bằng try-catch |

```java
// Checked Exception -- buộc phải try-catch hoặc throws, nếu không sẽ LỖI COMPILE
public void readFile(String path) throws IOException {
    FileReader fr = new FileReader(path); // có thể throw FileNotFoundException (con của IOException)
}

// Unchecked Exception -- không buộc, code vẫn compile được dù có thể crash lúc runtime
public int divide(int a, int b) {
    return a / b; // có thể throw ArithmeticException nếu b == 0, nhưng compiler không bắt khai báo
}
```

### Follow-up: Vì sao không nên dùng Exception để xử lý logic nghiệp vụ thông thường?

Vì việc throw/catch exception có overhead lớn hơn nhiều so với `if/else` thông thường (JVM phải
tạo stack trace đầy đủ tại thời điểm throw). Exception nên dành cho các **trường hợp ngoại lệ
thực sự** (exceptional case), không nên dùng để điều khiển luồng chương trình bình thường (ví dụ
dùng exception để thoát khỏi loop thay vì dùng `break`).

---

## Câu 2: `try-catch-finally` hoạt động ra sao? `finally` có luôn chạy không?

### Đáp án chi tiết

`finally` **luôn được thực thi**, bất kể `try` block có throw exception hay không, và bất kể
`try`/`catch` có câu lệnh `return` hay không — **ngoại trừ** các trường hợp:
- JVM bị tắt đột ngột (`System.exit()` được gọi trong try/catch).
- Thread bị kill (`Thread.stop()` — đã deprecated).
- Máy bị crash, mất điện...

### Câu hỏi "trap" kinh điển: `return` trong `finally` sẽ "đè" `return` trong `try`?

```java
public int test() {
    try {
        return 1;
    } finally {
        System.out.println("finally chạy");
    }
}
// Output khi gọi test(): in ra "finally chạy", rồi trả về 1
```

**Giải thích:** khi gặp `return 1` trong `try`, JVM **tính toán giá trị return (1) và lưu tạm**,
nhưng **không return ngay** — nó tạm dừng để chạy `finally` trước, sau đó mới thực sự return giá
trị đã lưu tạm.

```java
public int trickyTest() {
    int x = 1;
    try {
        return x;
    } finally {
        x = 2; // KHÔNG ảnh hưởng tới giá trị return, vì x đã được "chụp ảnh" (giá trị 1) trước khi finally chạy
    }
}
// trickyTest() trả về 1, KHÔNG phải 2
```

**Trường hợp đặc biệt nguy hiểm — `return` trong `finally` sẽ "nuốt" mất return/exception trong
try/catch:**

```java
public int dangerousTest() {
    try {
        return 1;
    } finally {
        return 2; // "đè" lên return 1 -- kết quả cuối cùng là 2
    }
}
// dangerousTest() trả về 2 -- giá trị return trong try bị "vô hiệu hóa"

public int evenMoreDangerous() {
    try {
        throw new RuntimeException("Lỗi!");
    } finally {
        return 2; // EXCEPTION BỊ NUỐT MẤT! Không có exception nào được throw ra ngoài
    }
}
// evenMoreDangerous() trả về 2 một cách "êm đẹp" -- exception "Lỗi!" biến mất hoàn toàn, rất nguy hiểm khi debug
```

**Bài học quan trọng**: **không bao giờ đặt `return` (hoặc `throw`/`break`/`continue`) trong block
`finally`** — đây được coi là anti-pattern và nhiều linter/checkstyle sẽ cảnh báo, vì nó có thể
âm thầm "nuốt" exception hoặc đè kết quả mong đợi, gây khó debug.

---

## Câu 3: `throw` và `throws` khác nhau ra sao?

### Đáp án chi tiết

- **`throw`**: từ khóa dùng để **ném ra một exception cụ thể** ngay tại thời điểm thực thi code.
  Dùng bên trong thân method.
  ```java
  if (age < 0) {
      throw new IllegalArgumentException("Age không thể âm");
  }
  ```

- **`throws`**: từ khóa dùng trong **khai báo signature của method**, để báo cho compiler (và
  người gọi method) biết rằng method này **có thể** ném ra những loại checked exception nào,
  buộc người gọi phải xử lý (try-catch hoặc tiếp tục `throws` lên trên).
  ```java
  public void readFile(String path) throws IOException, FileNotFoundException {
      // ...
  }
  ```

---

## Câu 4: Vì sao không nên `catch (Exception e) {}` (catch rỗng) hoặc catch quá chung?

### Đáp án chi tiết

**Catch rỗng (nuốt exception — "swallowing exception"):**

```java
try {
    riskyOperation();
} catch (Exception e) {
    // không làm gì cả -- ĐÂY LÀ ANTI-PATTERN NGUY HIỂM
}
```

Vấn đề: nếu có lỗi xảy ra, chương trình **âm thầm bỏ qua**, tiếp tục chạy với trạng thái có thể
không hợp lệ, dẫn tới bug rất khó tìm ra nguyên nhân gốc (vì không có log, không có stack trace).
Đây là một trong những lỗi khiến debug production rất khó khăn — lỗi xảy ra ở đâu đó upstream
nhưng biểu hiện ra (symptom) ở một chỗ hoàn toàn khác, xa nguyên nhân thực sự.

**Catch quá chung (`catch (Exception e)` thay vì catch cụ thể):**

```java
try {
    int[] arr = new int[5];
    arr[10] = 1; // ArrayIndexOutOfBoundsException
    int x = 5 / 0; // ArithmeticException
} catch (Exception e) { // bắt CHUNG mọi loại exception, không phân biệt xử lý khác nhau
    System.out.println("Có lỗi xảy ra");
}
```

Vấn đề: không thể xử lý khác nhau cho từng loại lỗi cụ thể (ví dụ `SQLException` cần retry kết nối
database, còn `IllegalArgumentException` cần trả lỗi 400 cho client — hai case này cần response
khác hẳn nhau). Catch chung `Exception` còn có rủi ro **vô tình bắt luôn cả những
`RuntimeException` không mong muốn** (bug thực sự trong code), khiến bug đó bị "che giấu" thay vì
được phát hiện và sửa.

**Cách làm đúng:**

```java
try {
    riskyOperation();
} catch (SQLException e) {
    log.error("Database error: ", e); // log đầy đủ để debug sau
    throw new ServiceException("Không thể kết nối CSDL", e); // wrap và rethrow nếu cần
} catch (IOException e) {
    log.error("IO error: ", e);
    throw new ServiceException("Lỗi đọc file", e);
}
```

---

## Câu 5: Custom Exception — khi nào và làm thế nào để tạo Exception riêng?

### Đáp án chi tiết

Nên tạo Custom Exception khi muốn diễn đạt một **lỗi nghiệp vụ cụ thể** (business error) một cách
rõ nghĩa, dễ phân biệt với các exception kỹ thuật chung của Java.

```java
public class InsufficientBalanceException extends RuntimeException {
    private final double shortageAmount;

    public InsufficientBalanceException(String message, double shortageAmount) {
        super(message);
        this.shortageAmount = shortageAmount;
    }

    public double getShortageAmount() {
        return shortageAmount;
    }
}

// Sử dụng:
public void withdraw(double amount) {
    if (amount > balance) {
        throw new InsufficientBalanceException(
            "Không đủ số dư", amount - balance);
    }
}
```

**Lưu ý lựa chọn kế thừa `Exception` hay `RuntimeException`:**
- Kế thừa `RuntimeException` (unchecked) nếu lỗi đó là **lỗi nghiệp vụ "không mong đợi"** mà
  caller không bắt buộc phải catch ở mọi nơi gọi (phổ biến hơn trong code hiện đại, đặc biệt với
  Spring Boot — để tránh "ô nhiễm" signature method với quá nhiều `throws`).
- Kế thừa `Exception` (checked) nếu muốn **bắt buộc** người gọi phải xử lý rõ ràng (ít phổ biến
  hơn trong codebase hiện đại vì gây cồng kềnh).

---

## Gợi ý câu hỏi tình huống bổ sung

1. Đưa đoạn code có `try-with-resources` và hỏi nó giải quyết vấn đề gì:
   ```java
   try (FileReader fr = new FileReader("file.txt")) {
       // ...
   } catch (IOException e) {
       // ...
   }
   // fr.close() được gọi TỰ ĐỘNG, kể cả khi có exception xảy ra trong try
   ```
   → Kiểm tra hiểu về `AutoCloseable`, và vì sao nó tốt hơn cách `finally { fr.close(); }` truyền
   thống (tránh quên gọi `close()`, tránh exception trong `finally` "nuốt" exception gốc trong try).
2. Hỏi: "Exception chaining (`new ServiceException(msg, originalException)`) có ích gì?" →
   Giữ lại **nguyên nhân gốc** (root cause) trong stack trace thông qua `getCause()`, giúp debug
   dễ hơn khi exception bị "wrap" qua nhiều layer (controller → service → repository).
