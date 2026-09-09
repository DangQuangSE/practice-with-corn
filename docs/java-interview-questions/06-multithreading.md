# 6. Multithreading cơ bản

Với junior, không cần hỏi sâu về `ExecutorService`, `CompletableFuture`, hay các thuật toán
lock-free — chỉ cần kiểm tra hiểu đúng các khái niệm nền tảng nhất.

## Câu 1: Thread và Runnable khác nhau ra sao? Cách nào nên dùng?

### Đáp án chi tiết

Có 2 cách tạo Thread trong Java:

**Cách 1: Extends `Thread`**
```java
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in thread");
    }
}

MyThread t = new MyThread();
t.start(); // KHÔNG gọi run() trực tiếp -- start() tạo thread mới rồi gọi run() bên trong thread đó
```

**Cách 2: Implements `Runnable`**
```java
public class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Running in thread");
    }
}

Thread t = new Thread(new MyTask());
t.start();
```

| Tiêu chí | Extends Thread | Implements Runnable |
|----------|----------------|----------------------|
| Đa kế thừa | Đã dùng hết "suất" extends (Java chỉ cho extends 1 class) — không thể extends thêm class khác | Vẫn có thể extends một class khác, vì Runnable chỉ là interface |
| Tách biệt "nhiệm vụ" và "cơ chế chạy" | Trộn lẫn — class vừa là Thread vừa là task | Tách biệt rõ — `MyTask` chỉ là logic công việc, không quan tâm nó được chạy bởi Thread nào |
| Khuyến nghị | Ít dùng hơn trong code hiện đại | **Được khuyến nghị** — linh hoạt hơn, đúng nguyên tắc "composition over inheritance" |

### Follow-up: Gọi `run()` trực tiếp (không qua `start()`) thì có gì khác?

Nếu gọi `t.run()` trực tiếp, code trong `run()` được thực thi **trên thread hiện tại** (ví dụ
main thread), giống như gọi một method bình thường — **không** tạo thread mới. Chỉ khi gọi
`t.start()`, JVM mới thực sự tạo một thread mới (cấp Stack riêng...) và gọi `run()` bên trong
thread đó.

---

## Câu 2: `synchronized` dùng để làm gì? Race Condition là gì?

### Đáp án chi tiết

**Race Condition** xảy ra khi **nhiều thread cùng truy cập và thay đổi một dữ liệu chia sẻ
(shared state) đồng thời**, và kết quả cuối cùng phụ thuộc vào **thứ tự thực thi** (timing) của
các thread — điều này không thể đoán trước, dẫn đến kết quả sai một cách "ngẫu nhiên" (không phải
lúc nào cũng tái hiện được bug).

```java
public class Counter {
    private int count = 0;

    public void increment() {
        count++; // KHÔNG phải 1 thao tác atomic! Thực ra gồm 3 bước: đọc count, +1, viết lại count
    }
}
```

**Tại sao `count++` không an toàn khi nhiều thread cùng gọi?**

Giả sử `count = 5`, hai thread A và B cùng gọi `increment()` gần như đồng thời:
1. Thread A đọc `count = 5`.
2. Thread B đọc `count = 5` (trước khi A viết lại).
3. Thread A tính `5 + 1 = 6`, viết lại `count = 6`.
4. Thread B tính `5 + 1 = 6` (dựa trên giá trị đã đọc trước đó), viết lại `count = 6`.

**Kết quả: `count = 6`, nhưng lẽ ra phải là `7`** (vì có 2 lần increment) — đây chính là race
condition, mất đi 1 lần tăng do hai thread "đọc-tính-viết" chồng lên nhau.

**`synchronized` giải quyết bằng cách nào?**

`synchronized` đảm bảo chỉ **một thread duy nhất** được thực thi đoạn code đó (hoặc giữ lock của
object đó) tại một thời điểm — các thread khác phải **chờ** tới khi thread đang giữ lock hoàn tất.

```java
public class Counter {
    private int count = 0;

    public synchronized void increment() { // lock trên "this" (current object)
        count++; // an toàn -- chỉ 1 thread được thực thi tại 1 thời điểm
    }
}
```

Có thể áp dụng `synchronized` cho:
- **Method**: lock trên `this` (instance method) hoặc trên `Class` object (static method).
- **Block code cụ thể**: `synchronized(lockObject) { ... }` — thường được khuyến nghị hơn vì chỉ
  khóa đúng đoạn code cần thiết, giảm tranh chấp (contention) không cần thiết.

```java
public void increment() {
    synchronized (this) {
        count++; // chỉ lock đúng phần code cần thiết, phần code khác trong method vẫn chạy tự do
    }
}
```

### Follow-up: Đánh đổi (trade-off) khi dùng `synchronized` là gì?

`synchronized` đảm bảo an toàn dữ liệu (thread-safety) nhưng **giảm hiệu năng** vì các thread phải
chờ nhau (mất đi tính song song thực sự khi truy cập đoạn code đó), và nếu dùng sai cách (lock
nhiều object theo thứ tự không nhất quán giữa các thread) có thể dẫn tới **deadlock** — hai (hay
nhiều) thread chờ nhau giữ lock của nhau mãi mãi, không bao giờ tiến triển được.

---

## Câu 3: `volatile` khác `synchronized` ra sao? (junior chỉ cần biết khái niệm tồn tại, không cần sâu)

### Đáp án chi tiết

**`volatile`** đảm bảo:
1. **Visibility**: khi một thread thay đổi giá trị biến `volatile`, thay đổi đó được **ghi thẳng
   xuống main memory ngay lập tức** (không bị giữ lại ở cache riêng của CPU/thread) và các thread
   khác đọc lại sẽ luôn thấy giá trị **mới nhất**.
2. Ngăn JVM/compiler **tối ưu hóa sai** thứ tự đọc/viết biến đó (instruction reordering) liên quan
   tới biến `volatile`.

**`volatile` KHÔNG đảm bảo tính atomic** cho các thao tác phức hợp như `count++` (vẫn là 3 bước:
đọc-tính-viết, vẫn có thể bị race condition dù biến là `volatile`). Trong khi đó, `synchronized`
đảm bảo cả tính atomic (chỉ 1 thread thực thi tại 1 thời điểm) lẫn visibility.

```java
private volatile boolean running = true; // dùng đúng chỗ: cờ tín hiệu đơn giản (flag)

public void stop() {
    running = false; // thread khác đọc running sẽ thấy giá trị mới ngay, không bị "cache cũ"
}

public void run() {
    while (running) {
        // làm việc...
    }
}
```

**Khi nào dùng `volatile`**: khi biến chỉ được **đọc/viết đơn giản** (không có thao tác phức hợp
như `++`), thường dùng làm flag tín hiệu dừng/bắt đầu giữa các thread. Khi cần thao tác phức hợp
an toàn, nên dùng `synchronized` hoặc các class trong `java.util.concurrent.atomic`
(`AtomicInteger`, `AtomicBoolean`...).

```java
private AtomicInteger count = new AtomicInteger(0);

public void increment() {
    count.incrementAndGet(); // atomic, không cần synchronized, hiệu năng tốt hơn nhờ CAS (Compare-And-Swap)
}
```

---

## Gợi ý câu hỏi tình huống bổ sung

1. "Deadlock là gì, cho ví dụ đơn giản?" → Hai thread, mỗi thread giữ 1 lock và đang chờ lock mà
   thread kia đang giữ → cả hai chờ mãi không bao giờ tiến triển.
   ```java
   // Thread 1: synchronized(lockA) { synchronized(lockB) { ... } }
   // Thread 2: synchronized(lockB) { synchronized(lockA) { ... } }
   // Nếu 2 thread chạy gần như đồng thời và mỗi bên đã giữ được lock đầu -> DEADLOCK
   ```
   Cách phòng tránh cơ bản: luôn lock các object theo **cùng một thứ tự cố định** ở mọi nơi trong
   code.
2. "`Thread.sleep()` có giải phóng lock đang giữ không?" → **Không.** `sleep()` chỉ làm thread tạm
   dừng, không nhường lock cho thread khác — đây khác với `wait()` (của `Object`), khi gọi `wait()`
   thread sẽ **nhường lock** lại để thread khác có thể vào được synchronized block.
