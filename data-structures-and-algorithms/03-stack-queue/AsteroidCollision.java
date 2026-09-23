import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Bài 3.9 — Asteroid Collision
 * Difficulty: Medium
 */
public class AsteroidCollision {
    public static int[] solve(int[] asteroids) {
        // TODO: stack mô phỏng va chạm giữa thiên thạch dương và âm.
        return new int[0];
    }

    public static void main(String[] args) {
        Check.expect("one collision",
                solve(new int[] { 5, 10, -5 }),
                new int[] { 5, 10 });
        Check.expect("all collide",
                solve(new int[] { 8, -8 }),
                new int[0]);
        Check.summary();
    }
}
