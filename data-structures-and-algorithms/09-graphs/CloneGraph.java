import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bài 9.4 — Clone Graph
 * Difficulty: Medium
 */
public class CloneGraph {
    static class GraphNode {
        int val;
        List<GraphNode> neighbors = new ArrayList<>();
        GraphNode(int val) { this.val = val; }
    }

    public static GraphNode solve(GraphNode node) {
        // TODO: map node gốc -> node clone để tránh lặp vô hạn.
        return null;
    }

    public static void main(String[] args) {
        Check.expect("empty graph", solve(null), null);
        Check.summary();
    }
}
