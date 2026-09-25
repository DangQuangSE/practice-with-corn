package dev.practice.workflows.domain;

import java.math.BigDecimal;

public class DiscountPolicy {
    /** FLOW-04: validate coupon dates/minimums/caps; use decimal arithmetic and never return a negative total. */
    public BigDecimal apply(BigDecimal subtotal, String couponCode) {
        throw new UnsupportedOperationException("TODO: implement FLOW-04 discount boundaries");
    }
}
