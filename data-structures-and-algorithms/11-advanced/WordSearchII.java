import java.util.ArrayList;
import java.util.List;

/**
 * Bài 11.6 — Word Search II
 * Difficulty: Medium
 */
public class WordSearchII {
    public static List<String> solve(char[][] board, String[] words) {
        // TODO: xây Trie rồi DFS trên board, cắt nhánh theo prefix.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        Check.expect("found words count",
                solve(new char[][] { { 'o', 'a' }, { 'e', 't' } },
                        new String[] { "oat", "eat" }).size(), 1);
        Check.summary();
    }
}
