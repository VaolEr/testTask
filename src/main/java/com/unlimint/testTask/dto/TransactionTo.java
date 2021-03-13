package com.unlimint.testTask.dto;

import java.io.Serializable;

//We can use lombok for simplify code
//@Getter - realises getters for all class variables;
//@Setter - realises setters for all class variables;
public class TransactionTo implements Serializable {

    //This class is used for transfer parsed Transaction object with string fields
    //to Transaction object with correct type fields.
    // Example: (TransactionTo) String currency -> (Transaction) Currency currency

    private static final long serialVersionUID = 1L;
    //Transaction id
    private String id;
    //Transaction amount
    private String amount;
    //Transaction currency
    private String currency;
    //Transaction comment
    private String comment;
    //Source file name
    private String filename;
    //Transaction line in source file name
    private Long line;
    //Parsing result
    private String result;

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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TransactionTo{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                ", filename='" + filename + '\'' +
                ", line='" + line + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
