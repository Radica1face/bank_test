package com.test.bank.business;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


public class PaymentGraph {

    private double yIns;                          //Годовая процентная ставка
    private double totalMoney;                    // Сумма погашения (основная сумма + проценты)
    private double totalInterests;                // Общая сумма процентов

    public List<RowGraph> makeGraph(Double yIns, Double loanMoney, Integer years, String date) {

        double mIns = yIns / 100 / 12;          // Ежемесячная процентная ставка
        int months = (years * 12);              // Месяцы
        double pow = Math.pow(1 + mIns,months);
        double remains = loanMoney;
        totalMoney = (months * loanMoney * mIns * pow) / (pow - 1);  // Общая сумма погашения
        totalMoney = Math.floor(totalMoney * 100 + 0.5) / 100;
        totalInterests = totalMoney - loanMoney;
        totalInterests = Math.floor(totalInterests * 100 + 0.5) / 100;  // итоговая сумма процентов

        LocalDate localDate = LocalDate.parse(date);

        ArrayList<RowGraph> arrayGraph = new ArrayList<RowGraph>();
        for (int i = 0; i < months; i++) {
            RowGraph graph = new RowGraph();
            if(i == months - 1) {
                localDate = localDate.plusMonths(1);
                graph.setDate(localDate);
                Double percents = remains * mIns;
                graph.setPercents(Math.floor(percents * 100 + 0.5) / 100);
                Double principal = remains;
                graph.setPrincipal(Math.floor(principal * 100 + 0.5) / 100);
                Double amount = graph.getPrincipal() + graph.getPercents();
                graph.setAmount(Math.floor(amount * 100 + 0.5) / 100);
                arrayGraph.add(graph);
                break;
            }
            // Из-за проблем с точностью фактическая основная сумма за последний месяц будет другой, и ее необходимо рассчитывать отдельно
            localDate = localDate.plusMonths(1);
            graph.setDate(localDate);
            Double percents = remains * mIns;                           // percents - это ежемесячный процент погашения;
            graph.setPercents(Math.floor(percents * 100 + 0.5) / 100);
            Double amount = totalMoney / months;                        // amount - это общий ежемесячный платеж
            graph.setAmount(Math.floor(amount * 100 + 0.5) / 100);
            Double principal = graph.getAmount() - graph.getPercents(); // principal - это ежемесячный основной платеж;
            graph.setPrincipal(Math.floor(principal * 100 + 0.5) / 100);
            remains -= graph.getPrincipal();

            arrayGraph.add(graph);
        }
        return arrayGraph;
    }

    public double getyIns() {
        return yIns;
    }

    public void setyIns(double yIns) {
        this.yIns = yIns;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getTotalInterests() {
        return totalInterests;
    }

    public void setTotalInterests(double totalInterests) {
        this.totalInterests = totalInterests;
    }

    public PaymentGraph() {
    }
}
