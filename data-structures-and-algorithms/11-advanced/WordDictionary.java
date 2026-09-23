import java.util.HashMap;
import java.util.Map;

/**
 * Bài 11.9 — Design Add and Search Words Data Structure
 * Difficulty: Medium
 */
public class WordDictionary {
    static class Dictionary {
        void addWord(String word) { /* TODO */ }
        boolean search(String pattern) {
            // TODO: khi gặp '.', DFS qua tất cả children.
            return false;
        }
    }

    public static boolean solve(String word, String pattern) {
        // TODO: add word rồi search pattern.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("wildcard search", solve("bad", ".ad"), true);
        Check.summary();
    }
}
