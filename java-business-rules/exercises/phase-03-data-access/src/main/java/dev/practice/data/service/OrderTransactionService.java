package dev.practice.data.service;

import dev.practice.data.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderTransactionService {
    private final OrderRepository orders;

    public OrderTransactionService(OrderRepository orders) {
        this.orders = orders;
    }

    @Transactional
    public void markCompleted(String orderId) {
        var order = orders.findById(orderId).orElseThrow();
        // TODO BE-12: apply one atomic local state/ledger change and demonstrate rollback on failure.
        // Do not call a remote provider while this database transaction is open.
        order.setOrderStatus("COMPLETED");
    }
}
