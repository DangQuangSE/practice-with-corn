import java.util.Arrays;
import java.util.Comparator;

/**
 * Bài 6.3 — Sắp xếp object theo nhiều tiêu chí
 * Difficulty: Easy
 *
 * Sắp xếp người theo tuổi giảm dần; nếu bằng tuổi, tên tăng dần.
 */
public class SortPeopleByComparator {
    public static String[] solve(String[] names, int[] ages) {
        // TODO: tạo object, dùng Comparator.comparingInt(...).reversed().thenComparing(...).
        return new String[0];
    }

    public static void main(String[] args) {
        Check.expect("age then name",
                solve(new String[] { "An", "Binh", "Chi" }, new int[] { 25, 30, 25 }),
                new String[] { "Binh", "An", "Chi" });
        Check.summary();
    }
}
