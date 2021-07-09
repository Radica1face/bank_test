package com.test.bank.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "credits")
public class Credit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "credit_limit")
    private Integer creditLimit;

    @Column(name = "interest_rate")
    private Double interestRate;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="bank_id")
    private Bank bank;

    @OneToMany (mappedBy="credit", fetch=FetchType.EAGER)
    private List<CreditSupply> supplies;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public List<CreditSupply> getSupplies() {
        return supplies;
    }

    public void setSupplies(List<CreditSupply> supplies) {
        this.supplies = supplies;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(id, credit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", creditLimit=" + creditLimit +
                ", interestRate=" + interestRate +
                ", bank=" + bank +
                '}';
    }
}
