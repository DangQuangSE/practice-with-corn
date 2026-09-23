import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Bài 9.7 — Dijkstra's Algorithm
 * Difficulty: Medium
 */
public class Dijkstra {
    public static int[] solve(int n, int[][] edges, int source) {
        // TODO: relax cạnh theo node có khoảng cách nhỏ nhất trong priority queue.
        return new int[n];
    }

    public static void main(String[] args) {
        Check.expect("shortest distances",
                solve(3, new int[][] { { 0, 1, 4 }, { 0, 2, 1 }, { 2, 1, 2 } }, 0),
                new int[] { 0, 3, 1 });
        Check.summary();
    }
}
