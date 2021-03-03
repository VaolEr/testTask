package com.unlimint.testTask.parsers;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.unlimint.testTask.dto.TransactionTo;
import com.unlimint.testTask.model.Transaction;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.unlimint.testTask.util.TransactionUtil.fromTransactionTos;

@Component
public class ParserCSV {

    private String fileName;

    public ParserCSV() {

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void readCsv(){
        //Build reader instance
        //Read data.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Start reading from line number 2 (line numbers start from zero)
        CSVReader reader = null;
        try {
            //Build reader instance
            CSVReader reader1 = new CSVReader(new FileReader(fileName), ',', '"', 0);
            //Read all rows at once
            List<String[]> allRows = reader1.readAll();
            //Read CSV line by line and use the string array as you want
            for(String[] row : allRows){
                System.out.println(Arrays.toString(row));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public List<Transaction> getListOfData() throws Exception{
        CsvToBean csv = new CsvToBean();
        //String csvFilename = "orders.csv";
        CSVReader csvReader = new CSVReader(new FileReader(fileName));
        //Set column mapping strategy
        List<TransactionTo> transactionTosList = csv.parse(setColumnMapping(), csvReader);
        List<Transaction> transactionsList = fromTransactionTos(transactionTosList);

        for (Transaction transaction : transactionsList) {
            System.out.println(transaction);
        }
        return transactionsList;
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
