import java.util.HashMap;
import java.util.Map;

/**
 * Bài 4.1 — Đếm tần suất xuất hiện của các phần tử
 * Difficulty: Easy
 */
public class FrequencyCount {
    public static Map<Integer, Integer> solve(int[] nums) {
        // TODO: dùng getOrDefault hoặc merge để đếm.
        return new HashMap<>();
    }

    public static void main(String[] args) {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        expected.put(2, 2);
        expected.put(3, 3);
        Check.expect("frequency map", solve(new int[] { 1, 2, 2, 3, 3, 3 }), expected);
        Check.summary();
    }
}
