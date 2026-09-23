/**
 * Bài 5.8 — Word Search
 * Difficulty: Medium
 */
public class WordSearch {
    public static boolean solve(char[][] board, String word) {
        // TODO: DFS bốn hướng, đánh dấu ô đang dùng rồi undo khi quay lui.
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        Check.expect("word exists", solve(board, "ABCCED"), true);
        Check.expect("word absent", solve(board, "ABCB"), false);
        Check.summary();
    }
}
