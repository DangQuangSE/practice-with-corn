import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Bài 4.7 — Thiết kế LRU Cache
 * Difficulty: Medium
 */
public class LRUCache {
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        // TODO: lấy giá trị và đánh dấu key vừa được dùng.
        return -1;
    }

    public void put(int key, int value) {
        // TODO: khi đầy, loại bỏ key ít được dùng gần đây nhất.
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        Check.expect("missing key", cache.get(1), -1);
        Check.summary();
    }
}
