/**
 * Bài 4.9 — Thiết kế HashMap từ đầu
 * Difficulty: Medium
 */
public class DesignHashMap {
    static class MyHashMap {
        void put(int key, int value) { /* TODO */ }
        int get(int key) { return -1; /* TODO */ }
        void remove(int key) { /* TODO */ }
    }

    public static int solve(int[] keys) {
        // TODO: dùng bucket array và chaining để xử lý collision.
        return 0;
    }

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(1, 10);
        Check.expect("missing key before implementation", map.get(2), -1);
        Check.summary();
    }
}
