package com.unlimint.testTask.util;

import com.unlimint.testTask.dto.TransactionTo;
import com.unlimint.testTask.model.Transaction;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionUtil {

    public static TransactionTo toTransactionTo(Transaction transaction){
        TransactionTo newTransactionTo = new TransactionTo();
        newTransactionTo.setId(transaction.getId().toString());
        newTransactionTo.setAmount(transaction.getAmount().toString());
        newTransactionTo.setCurrency(transaction.getCurrency().toString());
        newTransactionTo.setComment(transaction.getComment());
        return newTransactionTo;
    }

    public static List<TransactionTo> toTransactionTos(List<Transaction> transactions){
        return transactions.stream().map(TransactionUtil::toTransactionTo).collect(Collectors.toList());
    }

    public static Transaction fromTransactionTo(TransactionTo transactionTo) {
        Transaction newTransaction = new Transaction();
        newTransaction.setId(Integer.parseInt(transactionTo.getId()));
        newTransaction.setAmount(Double.parseDouble(transactionTo.getAmount()));
        newTransaction.setCurrency(Currency.getInstance(transactionTo.getCurrency()));
        newTransaction.setComment(transactionTo.getComment());
        return newTransaction;
    }

    public static List<Transaction> fromTransactionTos(List<TransactionTo> transactionTos){
        return transactionTos.stream().map(TransactionUtil::fromTransactionTo).collect(Collectors.toList());
    }
}
