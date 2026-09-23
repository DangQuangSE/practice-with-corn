import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bài 1.10 — Three Sum
 * Difficulty: Medium
 *
 * Tìm mọi bộ ba chỉ số khác nhau có tổng bằng 0; không trả về bộ ba trùng nhau.
 * Mục tiêu: O(n^2) sau khi sắp xếp.
 */
public class ThreeSum {

    public static List<List<Integer>> solve(int[] nums) {
        // TODO: sort, rồi dùng two pointers cho từng phần tử cố định.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println("ThreeSum");

        Check.expect("basic example",
                solve(new int[] { -1, 0, 1, 2, -1, -4 }),
                Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));

        Check.expect("no solution", solve(new int[] { 1, 2, -2 }), new ArrayList<>());

        Check.summary();
    }
}
