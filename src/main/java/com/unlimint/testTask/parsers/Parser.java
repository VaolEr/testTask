package com.unlimint.testTask.parsers;

import com.unlimint.testTask.model.Transaction;

import java.util.List;

public interface Parser {

    //This is base parser class

    /**
     * Method for define file name from where
     * parser will read data
     */
    public void setFileName(String fileName);

    /**
     * This method returns list of Transactions
     * {@link com.unlimint.testTask.model.Transaction}
     */
    public List<Transaction> getListOfData();
}
