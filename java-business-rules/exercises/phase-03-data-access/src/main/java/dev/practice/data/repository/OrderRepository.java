package dev.practice.data.repository;

import dev.practice.data.entity.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<PurchaseOrderEntity, String> {
    // TODO BE-13: add a fetch strategy for the read use case and compare generated SQL.
}
