package com.unlimint.testTask.dto;

import java.io.Serializable;

public class TransactionTo implements Serializable {

    private static final long serialVersionUID = 1L;
    //Transaction id
    private String id;
    //Transaction amount
    private String amount;
    //Transaction currency
    private String currency;
    //Transaction comment
    private String comment;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TransactionTo{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency=" + currency +
                ", comment='" + comment + '\'' +
                '}';
    }

}
