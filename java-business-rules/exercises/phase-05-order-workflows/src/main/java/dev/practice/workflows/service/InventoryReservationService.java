package dev.practice.workflows.service;

import dev.practice.workflows.persistence.InventoryItemEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryReservationService {
    /** FLOW-06: reserve or release stock atomically with the order state; validate positive quantity. */
    @Transactional
    public InventoryItemEntity reserve(InventoryItemEntity item, int quantity) {
        throw new UnsupportedOperationException("TODO: implement FLOW-06 reservation invariants");
    }

    /** BE-15: retry with a stable idempotency key; persist the result so duplicate requests are safe. */
    @Transactional
    public InventoryItemEntity reserveOnce(InventoryItemEntity item, int quantity, String idempotencyKey) {
        throw new UnsupportedOperationException("TODO: implement BE-15 idempotent retry");
    }
}
