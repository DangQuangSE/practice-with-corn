import java.util.Arrays;

/**
 * Bài 9.10 — Cheapest Flights Within K Stops
 * Difficulty: Medium
 */
public class CheapestFlightsWithinKStops {
    public static int solve(int n, int[][] flights, int source, int destination, int k) {
        // TODO: Bellman-Ford giới hạn k+1 lượt relax, hoặc BFS theo số stop.
        return -1;
    }

    public static void main(String[] args) {
        Check.expect("within one stop",
                solve(4, new int[][] {
                        { 0, 1, 100 }, { 1, 2, 100 }, { 2, 3, 100 }, { 0, 3, 500 }
                }, 0, 3, 1), 500);
        Check.summary();
    }
}
