/**
 * Bài 9.8 — Union-Find / Redundant Connection
 * Difficulty: Medium
 */
public class RedundantConnection {
    public static int[] solve(int[][] edges) {
        // TODO: nếu hai đầu cạnh đã cùng root thì đó là cạnh dư.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("redundant edge",
                solve(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } }),
                new int[] { 2, 3 });
        Check.summary();
    }
}
