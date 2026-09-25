package dev.practice.foundations.api;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponse(UUID id, String name, BigDecimal price, boolean deleted) {
}
