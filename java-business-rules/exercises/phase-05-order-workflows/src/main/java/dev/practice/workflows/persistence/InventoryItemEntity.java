package dev.practice.workflows.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class InventoryItemEntity {
    @Id
    private Long id;

    private int availableQuantity;

    /** BE-14: teach optimistic conflict detection; map the conflict to a retryable API response. */
    @Version
    private long version;

    protected InventoryItemEntity() {
    }

    public InventoryItemEntity(Long id, int availableQuantity) {
        this.id = id;
        this.availableQuantity = availableQuantity;
    }

    public Long getId() {
        return id;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public long getVersion() {
        return version;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
