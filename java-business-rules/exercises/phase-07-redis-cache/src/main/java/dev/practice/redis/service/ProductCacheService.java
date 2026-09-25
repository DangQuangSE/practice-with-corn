package dev.practice.redis.service;

import java.time.Duration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCacheService {
    private final StringRedisTemplate redis;
    private final ProductRepository repository;

    public ProductCacheService(StringRedisTemplate redis, ProductRepository repository) {
        this.redis = redis;
        this.repository = repository;
    }

    public String findDisplayName(long productId) {
        String key = "practice:product:" + productId;
        String cached = redis.opsForValue().get(key);
        if (cached != null) {
            return cached;
        }
        // INT-04: cache-aside miss; define a short TTL and behavior when Redis is unavailable.
        throw new UnsupportedOperationException("TODO: load source-of-truth value and cache it");
    }

    public void invalidate(long productId) {
        // TODO: invalidate after the PostgreSQL write commits; Redis is not the source of truth.
        redis.delete("practice:product:" + productId);
    }

    public Duration suggestedTtl() {
        return Duration.ofMinutes(5);
    }

    public interface ProductRepository {
        String findDisplayNameById(long productId);
    }
}
