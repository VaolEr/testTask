package com.unlimint.testTask.model;

import java.io.Serializable;
import java.util.Currency;
import java.util.Objects;

public class Transaction {

    //Transaction id
    private Integer id;
    //Transaction amount
    private Double amount;
    //Transaction currency
    private Currency currency;
    //Transaction comment
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && amount == that.amount && currency.equals(that.currency) && comment.equals(that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, comment);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency=" + currency +
                ", comment='" + comment + '\'' +
                '}';
    }
}
