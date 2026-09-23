import java.util.HashMap;
import java.util.Map;

/**
 * Bài 11.5 — Implement Trie
 * Difficulty: Medium
 */
public class TrieImplementation {
    static class Trie {
        void insert(String word) { /* TODO */ }
        boolean search(String word) { return false; /* TODO */ }
        boolean startsWith(String prefix) { return false; /* TODO */ }
    }

    public static boolean solve(String word, String prefix) {
        // TODO: tạo Trie, insert word, rồi kiểm tra search/startsWith.
        return false;
    }

    public static void main(String[] args) {
        Check.expect("prefix exists", solve("apple", "app"), true);
        Check.summary();
    }
}
