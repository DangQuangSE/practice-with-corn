# 1. OOP căn bản

## Câu 1: Hãy nêu 4 tính chất của OOP và cho ví dụ thực tế cho mỗi tính chất

### Đáp án chi tiết

**1. Encapsulation (Tính đóng gói)**

Là việc che giấu dữ liệu nội bộ (state) của object, chỉ cho phép truy cập/thay đổi thông qua các
phương thức công khai (getter/setter, hoặc các method nghiệp vụ). Mục đích là bảo vệ tính nhất quán
của dữ liệu (data integrity) — không cho bên ngoài thay đổi state một cách tùy tiện, có thể gây ra
trạng thái không hợp lệ.

```java
public class BankAccount {
    private double balance; // private -> không ai truy cập trực tiếp được

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải > 0");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalStateException("Không đủ tiền");
        }
        this.balance -= amount;
    }

    public double getBalance() {
        return balance; // chỉ cho đọc, không cho set trực tiếp
    }
}
```

Nếu `balance` là `public`, ai cũng có thể set `account.balance = -1000000;` — phá vỡ tính đúng đắn
của nghiệp vụ. Encapsulation ngăn chặn điều này bằng cách bắt buộc đi qua các method có kiểm tra
logic (validation).

**2. Inheritance (Tính kế thừa)**

Cho phép một class (subclass/child) tái sử dụng thuộc tính và phương thức của một class khác
(superclass/parent), đồng thời có thể mở rộng hoặc thay đổi hành vi.

```java
public class Animal {
    protected String name;

    public void eat() {
        System.out.println(name + " is eating");
    }
}

public class Dog extends Animal {
    public void bark() {
        System.out.println(name + " is barking");
    }
}
```

`Dog` kế thừa `eat()` từ `Animal` mà không cần viết lại, đồng thời có thêm hành vi riêng `bark()`.

**3. Polymorphism (Tính đa hình)**

Là khả năng một đối tượng có thể "biến hình" — cùng một lời gọi method nhưng hành vi thực thi khác
nhau tùy vào kiểu thực sự (runtime type) của object. Có 2 loại:

- **Compile-time polymorphism** (overloading): chọn method dựa vào signature lúc compile.
- **Runtime polymorphism** (overriding): chọn method dựa vào kiểu thực sự của object lúc runtime
  (dynamic binding / late binding), thông qua cơ chế **virtual method table (vtable)** trong JVM.

```java
public class Animal {
    public void makeSound() {
        System.out.println("Some generic sound");
    }
}

public class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Cat(); // reference type Animal, object type Cat
        a.makeSound(); // In ra "Meow" — quyết định bởi object thực sự, không phải reference
    }
}
```

Đây là điểm hay bị junior trả lời sai: họ nghĩ method được gọi phụ thuộc vào *kiểu của biến tham
chiếu* (`Animal a`), nhưng thực ra JVM dùng **dynamic dispatch** dựa trên kiểu thực sự của object
trong heap.

**4. Abstraction (Tính trừu tượng)**

Là việc chỉ hiển thị những thông tin/hành vi cần thiết, ẩn đi chi tiết triển khai phức tạp bên
trong. Thực hiện qua `abstract class` hoặc `interface`.

```java
public interface PaymentMethod {
    void pay(double amount);
}

public class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        // chi tiết xử lý phức tạp: gọi API ngân hàng, xác thực OTP...
        System.out.println("Paid " + amount + " via credit card");
    }
}
```

Người dùng `PaymentMethod` không cần biết bên trong `pay()` xử lý ra sao, chỉ cần biết gọi `pay()`.

---

## Câu 2: Overloading và Overriding khác nhau như thế nào?

### Đáp án chi tiết

| Tiêu chí | Overloading | Overriding |
|----------|-------------|------------|
| Thời điểm quyết định | Compile-time (static binding) | Runtime (dynamic binding) |
| Vị trí | Cùng 1 class (hoặc class con khai báo thêm method cùng tên khác signature) | Class con và class cha (quan hệ kế thừa) |
| Signature | Phải khác nhau (số lượng, kiểu, hoặc thứ tự parameter) | Phải **giống hệt** (cùng tên, cùng parameter) |
| Return type | Có thể khác | Phải cùng kiểu hoặc là **covariant return type** (kiểu con của return type gốc) |
| Access modifier | Không liên quan | Không được **thu hẹp** quyền truy cập (ví dụ cha `protected` thì con không thể override thành `private`) |
| Từ khóa `static`, `final`, `private` | Không ảnh hưởng | Method `static`, `final`, `private` **không override được** |
| Exception | Không bị giới hạn | Không được throw checked exception **rộng hơn** method cha |

```java
// Overloading
public class Calculator {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
    public int add(int a, int b, int c) { return a + b + c; }
}

// Overriding
public class Shape {
    public double getArea() { return 0; }
}

public class Circle extends Shape {
    private double radius;

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
```

### Câu hỏi đào sâu (follow-up): Constructor có override được không?

**Không.** Vì:
1. Constructor không được kế thừa (inherit) — mỗi class phải tự định nghĩa constructor riêng
   (hoặc dùng default constructor).
2. Override yêu cầu cùng tên method, nhưng tên constructor luôn là tên class, nên class cha và
   class con có constructor tên khác nhau — không thể "giống hệt" để override.
3. Tuy nhiên, constructor có thể bị **overload** (nhiều constructor cùng class, khác tham số), và
   class con có thể gọi constructor cha qua `super(...)`.

---

## Câu 3: Interface và Abstract class khác nhau ở điểm nào? Khi nào dùng cái nào?

### Đáp án chi tiết

| Tiêu chí | Abstract Class | Interface |
|----------|---------------|-----------|
| Đa kế thừa | Một class chỉ extends được **1** abstract class | Một class implements được **nhiều** interface |
| Constructor | Có thể có constructor | Không có constructor |
| State (field) | Có thể có instance field với mọi access modifier | Chỉ có `public static final` field (constant), không có instance state thông thường |
| Method | Có thể có method đã implement (concrete) lẫn abstract | Trước Java 8: chỉ có abstract method. Từ Java 8+: có thêm `default` và `static` method. Từ Java 9+: có thêm `private` method |
| Access modifier của method | Có thể `public`, `protected`, `private` | Mặc định `public` (abstract method), `default`/`static` có thể có thân nhưng vẫn `public` |
| Mục đích thiết kế | Diễn tả quan hệ **"is-a"** chặt, có chia sẻ code triển khai chung | Diễn tả **"can-do"** / khả năng (capability), tách hợp đồng (contract) khỏi triển khai |

```java
// Abstract class: chia sẻ code triển khai chung
public abstract class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void printBrand() {
        System.out.println("Brand: " + brand);
    }

    public abstract void move(); // bắt buộc class con tự implement
}

// Interface: định nghĩa khả năng
public interface Flyable {
    void fly();

    default void takeOff() { // default method từ Java 8
        System.out.println("Taking off...");
    }
}

public class Airplane extends Vehicle implements Flyable {
    public Airplane(String brand) {
        super(brand);
    }

    @Override
    public void move() {
        System.out.println("Airplane moving on runway");
    }

    @Override
    public void fly() {
        System.out.println("Airplane flying");
    }
}
```

### Khi nào dùng cái nào?

- Dùng **interface** khi muốn định nghĩa một hợp đồng (contract) mà nhiều class không liên quan
  về mặt phân cấp (hierarchy) đều có thể implement (ví dụ `Comparable`, `Runnable`, `Serializable`).
- Dùng **abstract class** khi các class con có quan hệ "is-a" rõ ràng và **chia sẻ code triển
  khai chung** (không muốn lặp code ở mỗi class con).
- Một nguyên tắc kinh điển: "Program to an interface, not an implementation" — ưu tiên thiết kế
  dựa trên interface để giảm coupling.

### Follow-up: Từ Java 8 có default method rồi, vậy interface có "thay thế" được abstract class không?

**Không hoàn toàn.** Vì interface vẫn không có state (instance field) và không có constructor.
Default method trong interface chỉ giải quyết vấn đề "thêm method mới vào interface mà không phá
vỡ các class đã implement nó từ trước" (tránh breaking change), không nhằm biến interface thành
abstract class.

---

## Câu 4: `this` và `super` khác nhau ra sao?

### Đáp án chi tiết

- **`this`**: tham chiếu đến **đối tượng hiện tại** (instance đang được gọi method/constructor).
  Dùng để: phân biệt field với parameter cùng tên, gọi constructor khác trong cùng class
  (`this(...)`), truyền chính đối tượng hiện tại ra ngoài.

- **`super`**: tham chiếu đến **class cha trực tiếp**. Dùng để: gọi constructor của cha
  (`super(...)`, phải là statement đầu tiên trong constructor con), gọi method của cha đã bị
  override (`super.methodName()`), truy cập field của cha khi bị che (shadow) bởi field cùng tên ở
  class con.

```java
public class Person {
    protected String name;

    public Person(String name) {
        this.name = name; // this.name (field) = name (parameter)
    }

    public void introduce() {
        System.out.println("I am " + name);
    }
}

public class Student extends Person {
    private String school;

    public Student(String name, String school) {
        super(name); // gọi constructor cha — phải là dòng đầu tiên
        this.school = school;
    }

    @Override
    public void introduce() {
        super.introduce(); // gọi lại method gốc của cha
        System.out.println("I study at " + school);
    }
}
```

---

## Gợi ý câu hỏi tình huống (để tránh học vẹt)

1. "Composition và Inheritance khác nhau ra sao, vì sao nhiều chỗ khuyên nên 'favor composition
   over inheritance'?" → Junior hiểu sâu sẽ nói tới: inheritance tạo coupling chặt giữa class
   cha-con (thay đổi class cha ảnh hưởng toàn bộ class con — "fragile base class problem"), trong
   khi composition linh hoạt hơn, dễ thay đổi hành vi tại runtime.
2. Đưa đoạn code có method `private` ở class cha, hỏi "class con có override được không, in ra gì
   khi gọi qua reference cha?" → Kiểm tra hiểu rõ method `private`/`static` không tham gia dynamic
   dispatch (bị "method hiding" thay vì "overriding").
