import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bài 4.4 — Group Anagrams
 * Difficulty: Medium
 */
public class GroupAnagrams {
    public static List<List<String>> solve(String[] words) {
        // TODO: tạo canonical key rồi gom vào HashMap.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("group by sorted key",
                solve(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }).toString(),
                "[[eat, tea, ate], [tan, nat], [bat]]");
        Check.summary();
    }
}
