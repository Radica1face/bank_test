package com.test.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.TypeDef;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 10000, message = "Сумма кредита не менее 10 000")
    @Max(value = 10000000, message = "Сумма кредита не более 10 000 000")
    @Column(name = "loan_money")
    private double loanMoney;

    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 1, message = "Период кредитования не менее 1 года")
    @Max(value = 30, message = "Период кредитования не более 30 лет")
    @Column(name = "years")
    private int years;

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

    public double getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(double loanMoney) {
        this.loanMoney = loanMoney;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
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
