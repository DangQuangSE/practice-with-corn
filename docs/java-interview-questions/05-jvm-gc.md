# 5. JVM & Garbage Collection (cơ bản)

Junior không cần trả lời chi tiết tới mức implement GC algorithm, nhưng nên hiểu được **bản chất
khái niệm**, vì điều này ảnh hưởng trực tiếp tới cách viết code tránh memory leak.

## Câu 1: Garbage Collection là gì? Object bị thu hồi (garbage collected) khi nào?

### Đáp án chi tiết

**Garbage Collection (GC)** là cơ chế tự động của JVM nhằm tìm và giải phóng các object trên Heap
**không còn được sử dụng nữa** (không còn reachable từ chương trình), để tái sử dụng vùng nhớ đó.
Đây là điểm khác biệt lớn so với C/C++ — Java developer không cần tự gọi `free()`/`delete`.

**Một object được coi là "có thể thu hồi" (eligible for GC) khi nó không còn reachable từ bất kỳ
GC Root nào.**

**GC Root** là các điểm khởi đầu mà GC dùng để xác định "đồ thị reachability" — bao gồm:
- Biến local đang active trên Stack của các thread đang chạy.
- Static field của class đã được load.
- Tham chiếu từ JNI (native code).

Nếu đi từ tất cả GC Root mà **không có đường reference nào** dẫn tới một object → object đó là
rác (garbage), sẽ được GC dọn ở lần chạy tiếp theo.

```java
public void example() {
    Person p = new Person("Alice"); // p trỏ tới object Person trên Heap -- object có thể reach từ GC Root (biến local p)
    p = null; // object Person("Alice") không còn ai trỏ tới -- ĐỦ ĐIỀU KIỆN để bị GC thu hồi
}

public void example2() {
    Person p1 = new Person("Bob");
    Person p2 = p1;
    p1 = null; // object Person("Bob") VẪN reachable qua p2 -- CHƯA bị GC, vì vẫn có ít nhất 1 đường reference
}
```

### Follow-up: Có thể "ép" GC chạy ngay không? `System.gc()` có đảm bảo dọn rác ngay không?

**Không đảm bảo.** `System.gc()` chỉ là một **gợi ý (hint)** gửi tới JVM rằng "đây có thể là thời
điểm tốt để chạy GC", nhưng JVM **không bắt buộc** phải tuân theo ngay lập tức — JVM tự quyết định
khi nào thực sự chạy GC dựa trên chiến lược nội bộ của nó. Vì vậy không nên dựa vào `System.gc()`
trong code production để "dọn rác theo ý muốn".

---

## Câu 2: Memory Leak có thể xảy ra trong Java (ngôn ngữ có GC) không? Cho ví dụ.

### Đáp án chi tiết

**Có**, mặc dù Java có GC tự động, memory leak vẫn xảy ra khi code **giữ reference tới object một
cách không cần thiết**, khiến object đó **luôn reachable** từ GC Root → GC không bao giờ thu hồi
được, dù logic chương trình không còn cần dùng tới nó nữa.

**Các nguyên nhân memory leak phổ biến:**

1. **Static collection giữ reference mãi mãi:**
   ```java
   public class Cache {
       private static List<Object> cache = new ArrayList<>();

       public static void add(Object obj) {
           cache.add(obj); // object được add vào nhưng KHÔNG BAO GIỜ remove
       }
   }
   // -> cache (static field) là GC Root, mọi object add vào sẽ "sống" mãi cho tới khi JVM tắt
   // -> đây là nguyên nhân OutOfMemoryError kinh điển trong production
   ```

2. **Listener / callback không được unregister:**
   ```java
   button.addClickListener(myListener); // myListener được giữ reference bởi button
   // nếu quên gọi button.removeClickListener(myListener) khi không cần nữa
   // -> myListener (và toàn bộ object mà nó tham chiếu tới) không bao giờ bị GC
   ```

3. **Inner class không-static giữ reference ngầm tới outer class:**
   ```java
   public class OuterClass {
       private byte[] bigData = new byte[100_000_000]; // 100MB

       class InnerClass { // non-static inner class luôn giữ reference NGẦM tới instance OuterClass
           void doSomething() { /* ... */ }
       }

       public InnerClass createInner() {
           return new InnerClass();
       }
   }
   // nếu InnerClass instance "sống" lâu (ví dụ được lưu ở đâu đó toàn cục)
   // -> nó giữ OuterClass sống theo -> 100MB bigData KHÔNG được GC dù không còn cần OuterClass nữa
   ```

4. **`ThreadLocal` không được `remove()`** trong môi trường thread pool (web server) — vì thread
   trong pool được tái sử dụng, nếu không `remove()`, dữ liệu cũ "rò" sang request tiếp theo dùng
   chung thread đó, và cũng giữ object sống lâu hơn cần thiết.

**Cách phát hiện/phòng tránh**: dùng tool như VisualVM, JProfiler, hoặc heap dump kết hợp Eclipse
MAT để tìm "retained object" lớn bất thường. Về coding practice: luôn `remove()`/`unregister()`
listener khi không cần, dùng `WeakReference` cho cache không quan trọng, cẩn trọng với static
collection.

---

## Câu 3: JVM có những vùng nhớ chính nào? (chỉ cần biết khái niệm, không cần implement)

### Đáp án chi tiết

| Vùng nhớ | Vai trò |
|----------|---------|
| **Heap** | Lưu tất cả object và array. Chia thành Young Generation (Eden, Survivor) và Old Generation — phục vụ chiến lược GC theo "thế hệ" (generational GC), dựa trên quan sát thực tế rằng đa số object "chết" rất nhanh sau khi tạo. |
| **Stack** | Mỗi thread có 1 stack riêng, lưu stack frame của các method đang gọi (local variable, tham số, địa chỉ return). |
| **Method Area / Metaspace** (từ Java 8, thay cho PermGen cũ) | Lưu metadata của class đã load: thông tin class, method, static field, constant pool. |
| **PC Register** | Lưu địa chỉ instruction (bytecode) đang được thực thi của từng thread. |
| **Native Method Stack** | Phục vụ cho việc gọi native method (qua JNI). |

**Vì sao cần biết Young/Old Generation?** Để hiểu vì sao GC thường chạy "Minor GC" (dọn Young Gen,
nhanh, thường xuyên) khác với "Major/Full GC" (dọn cả Old Gen, chậm hơn, ít xảy ra hơn) — object
mới tạo nằm ở Eden, nếu "sống sót" qua vài lần Minor GC sẽ được "thăng cấp" (promote) lên Old Gen.

---

## Gợi ý câu hỏi tình huống bổ sung

1. "`StackOverflowError` và `OutOfMemoryError` khác nhau ra sao, nguyên nhân thường gặp của mỗi
   loại?" → `StackOverflowError`: thường do đệ quy vô hạn hoặc quá sâu (mỗi lời gọi method thêm 1
   stack frame, vượt quá kích thước Stack được cấp). `OutOfMemoryError: Java heap space`: Heap đầy
   do tạo quá nhiều object không được GC (thường do memory leak hoặc xử lý dữ liệu quá lớn cùng
   lúc).
2. "Có thể chủ động set một object thành `null` để giúp GC nhanh hơn không?" → Có thể giúp trong
   một số trường hợp cụ thể (ví dụ object lớn không còn cần trong khi method vẫn đang chạy lâu),
   nhưng **không cần làm điều này một cách thường lệ** — trong đa số trường hợp, khi biến local ra
   khỏi scope (method kết thúc), JVM tự nhận biết object không còn reachable.
