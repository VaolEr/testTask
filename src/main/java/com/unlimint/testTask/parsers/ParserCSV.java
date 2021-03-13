package com.unlimint.testTask.parsers;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.unlimint.testTask.dto.TransactionTo;
import com.unlimint.testTask.model.Transaction;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static com.unlimint.testTask.util.TransactionUtil.fromTransactionTos;

@Component
public class ParserCSV implements Parser{

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    /**
     * Method for define file name from where
     * CSV parser will read data
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Transaction> getListOfData() {
        try {
            CsvToBean csv = new CsvToBean();
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            //Set column mapping strategy
            List<TransactionTo> transactionTosList = csv.parse(setColumnMapping(), csvReader);
            csvReader.close();
            for (int i = 0; i < transactionTosList.size(); i++) {
                TransactionTo transactionTo = transactionTosList.get(i);
                transactionTo.setLine((long) i + 1);
                transactionTo.setFilename(fileName);
            }
//        for (Transaction transaction : transactionsList) {
//            System.out.println(transaction);
//        }
            return fromTransactionTos(transactionTosList);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static ColumnPositionMappingStrategy setColumnMapping()
    {
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(TransactionTo.class);
        String[] columns = new String[] {"id", "amount", "currency", "comment"};
        strategy.setColumnMapping(columns);
        return strategy;
    }

}
