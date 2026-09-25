package dev.practice.data.api;

import java.math.BigDecimal;

public record OrderSummary(String orderId, String accountId, BigDecimal totalPrice, int lineCount) {
}
