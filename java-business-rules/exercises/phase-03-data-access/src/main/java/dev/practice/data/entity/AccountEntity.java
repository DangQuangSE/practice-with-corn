package dev.practice.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @Column(name = "account_id", length = 50)
    private String accountId;

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "account")
    private List<PurchaseOrderEntity> orders = new ArrayList<>();

    protected AccountEntity() {
    }

    public String getAccountId() { return accountId; }
    public String getCustomerName() { return customerName; }
    public BigDecimal getBalance() { return balance; }
    public List<PurchaseOrderEntity> getOrders() { return orders; }
}
