package dev.practice.workflows.domain;

public class OrderTransitionPolicy {
    /** FLOW-05: define legal transitions; reject every transition not explicitly allowed. */
    public OrderStatus transition(OrderStatus current, OrderStatus requested) {
        throw new UnsupportedOperationException("TODO: implement FLOW-05 transition rules");
    }
}
