package com.test.bank.business;


import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.Date;

public class RowGraph {

    private LocalDate date;
    private Double principal;
    private Double percents;
    private Double amount;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrincipal() {
        return principal;
    }

    public void setPrincipal(Double principal) {
        this.principal = principal;
    }

    public Double getPercents() {
        return percents;
    }

    public void setPercents(Double percents) {
        this.percents = percents;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public RowGraph() {
    }

    public RowGraph(LocalDate date, Double principal, Double percents, Double amount) {
        this.date = date;
        this.principal = principal;
        this.percents = percents;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "date='" + date + '\'' +
                ", principal=" + principal +
                ", percents=" + percents +
                ", amount=" + amount +
                '}';
    }
}
