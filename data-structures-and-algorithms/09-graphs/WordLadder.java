import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Bài 9.9 — Word Ladder
 * Difficulty: Medium
 */
public class WordLadder {
    public static int solve(String beginWord, String endWord, List<String> wordList) {
        // TODO: BFS; sinh từ hàng xóm bằng cách thay từng ký tự.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("shortest transformation",
                solve("hit", "cog",
                        List.of("hot", "dot", "dog", "lot", "log", "cog")), 5);
        Check.summary();
    }
}
