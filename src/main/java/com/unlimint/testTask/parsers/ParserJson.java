package com.unlimint.testTask.parsers;

import com.unlimint.testTask.dto.TransactionTo;
import com.unlimint.testTask.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.unlimint.testTask.util.TransactionUtil.fromTransactionTos;

@Component
public class ParserJson implements Parser{

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    /**
     * Method for define file name from where
     * Json parser will read data
     */
    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> getListOfData(){

        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(fileName);
            //Read JSON file
            Object obj = jsonParser.parse(fileReader);
            JSONArray transactionsAsJsonObjectsList = (JSONArray) obj;
            fileReader.close();
            List<TransactionTo> transactionTosList = new ArrayList<>();

            //Iterate over transactionInJson array
            transactionsAsJsonObjectsList.forEach( transactionInJson -> transactionTosList.add(parseJsonObjectToTransactionTo( (JSONObject) transactionInJson )) );
            for (int i = 0; i < transactionTosList.size(); i++) {
                TransactionTo transactionTo = transactionTosList.get(i);
                transactionTo.setLine((long) i+1);
                transactionTo.setFilename(fileName);
            }
//            for (Transaction transaction : transactionsList) {
//                System.out.println(transaction);
//            }
            return fromTransactionTos(transactionTosList);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static TransactionTo parseJsonObjectToTransactionTo(JSONObject transaction)
    {
        TransactionTo transactionTo = new TransactionTo();

        //Get transaction id
        transactionTo.setId(transaction.get("orderId").toString());

        //Get transaction amount
        transactionTo.setAmount(transaction.get("amount").toString());

        //Get transaction currency
        transactionTo.setCurrency(transaction.get("currency").toString());

        //Get transaction comment
        transactionTo.setComment(transaction.get("comment").toString());

        return transactionTo;
    }

}
