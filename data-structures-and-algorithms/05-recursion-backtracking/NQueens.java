import java.util.ArrayList;
import java.util.List;

/**
 * Bài 5.7 — N-Queens
 * Difficulty: Medium
 */
public class NQueens {
    public static List<List<String>> solve(int n) {
        // TODO: đặt từng hàng, kiểm tra cột và hai đường chéo trước khi chọn.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("four queens", solve(4).size(), 2);
        Check.expect("one queen", solve(1).size(), 1);
        Check.summary();
    }
}
