package dev.practice.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class PurchaseOrderEntity {
    @Id
    @Column(name = "order_id", length = 50)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "order_status", nullable = false, length = 20)
    private String orderStatus;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderLineEntity> orderLines = new ArrayList<>();

    protected PurchaseOrderEntity() {
    }

    public String getOrderId() { return orderId; }
    public AccountEntity getAccount() { return account; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public String getOrderStatus() { return orderStatus; }
    public List<OrderLineEntity> getOrderLines() { return orderLines; }

    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}
