package dev.practice.data.service;

import dev.practice.data.api.OrderSummary;
import dev.practice.data.repository.OrderRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderReadService {
    private final OrderRepository orders;

    public OrderReadService(OrderRepository orders) {
        this.orders = orders;
    }

    @Transactional(readOnly = true)
    public List<OrderSummary> summaries() {
        // TODO BE-11/13: map to a narrow DTO, observe lazy loads, then choose EntityGraph/projection/fetch join.
        return orders.findAll().stream()
                .map(order -> new OrderSummary(
                        order.getOrderId(),
                        order.getAccount().getAccountId(),
                        order.getTotalPrice(),
                        order.getOrderLines().size()))
                .toList();
    }
}
