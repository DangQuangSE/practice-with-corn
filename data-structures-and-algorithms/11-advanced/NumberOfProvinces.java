/**
 * Bài 11.7 — Number of Provinces
 * Difficulty: Medium
 */
public class NumberOfProvinces {
    public static int solve(int[][] isConnected) {
        // TODO: Union-Find hoặc DFS trên ma trận kề.
        return 0;
    }

    public static void main(String[] args) {
        Check.expect("two provinces",
                solve(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }), 2);
        Check.summary();
    }
}
