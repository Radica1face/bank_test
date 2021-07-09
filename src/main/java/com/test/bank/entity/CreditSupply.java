package com.test.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "supplies")
public class CreditSupply {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne (optional=false, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn (name="client_id")
    private Client client;

    @ManyToOne (optional=false, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn (name="credit_id")
    private Credit credit;

    @Column(name = "loan_money")
    private Double loanMoney;

    @Column(name = "years")
    private Integer years;

    @Column(name = "date")
    private String date;

    public CreditSupply(Client client, Credit credit, Double loanMoney, Integer years, String date) {
        this.client = client;
        this.credit = credit;
        this.loanMoney = loanMoney;
        this.years = years;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(Double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditSupply that = (CreditSupply) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CreditSupply{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", loanMoney=" + loanMoney +
                ", years=" + years +
                ", date='" + date + '\'' +
                '}';
    }
}
