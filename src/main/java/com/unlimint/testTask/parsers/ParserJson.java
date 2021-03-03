package com.unlimint.testTask.parsers;

//import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.testTask.dto.TransactionTo;
import com.unlimint.testTask.model.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.unlimint.testTask.util.TransactionUtil.fromTransactionTos;

@Component
public class ParserJson {


    @SuppressWarnings("unchecked")
    public List<Transaction> getListOfData(){

        try {
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader("orders.json");
            //Read JSON file
            Object obj = jsonParser.parse(fileReader);
            JSONArray transactionsAsJsonObjectsList = (JSONArray) obj;
            System.out.println(transactionsAsJsonObjectsList);

            List<TransactionTo> transactionTosList = new ArrayList<>();
            //Iterate over employee array
            transactionsAsJsonObjectsList.forEach( transactionInJson -> transactionTosList.add(parseJsonObjectToTransactionTo( (JSONObject) transactionInJson )) );
            List<Transaction> transactionsList = fromTransactionTos(transactionTosList);

            for (Transaction transaction : transactionsList) {
                System.out.println(transaction);
            }

            return transactionsList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
//        ObjectMapper mapper = new ObjectMapper();
//        TransactionTo transactionTo = mapper.readValue(jsonString, TransactionTo.class);
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
