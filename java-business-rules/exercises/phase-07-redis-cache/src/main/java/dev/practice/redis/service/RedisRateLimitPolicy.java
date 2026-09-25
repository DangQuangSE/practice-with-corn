package dev.practice.redis.service;

import java.time.Duration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRateLimitPolicy {
    private final StringRedisTemplate redis;

    public RedisRateLimitPolicy(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public boolean allow(String opaqueSubjectKey, int limit, Duration window) {
        // BE-16: implement atomically (for example a bounded Lua script); avoid race-prone GET then INCR.
        // Hash or pseudonymize identifiers; set expiry once and define the behavior on Redis outage.
        throw new UnsupportedOperationException("TODO: implement rate-limit decision");
    }
}
