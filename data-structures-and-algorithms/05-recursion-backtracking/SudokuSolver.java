/**
 * Bài 5.9 — Giải Sudoku
 * Difficulty: Medium
 */
public class SudokuSolver {
    public static boolean solve(char[][] board) {
        // TODO: chọn ô trống, thử 1..9, kiểm tra hàng/cột/ô 3x3 rồi quay lui.
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        Check.expect("solvable board", solve(board), true);
        Check.summary();
    }
}
