package com.unlimint.testTask;

import com.unlimint.testTask.model.Transaction;
import com.unlimint.testTask.parsers.ParserCSV;
import com.unlimint.testTask.parsers.ParserJson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TestTaskApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestTaskApplication.class, args);

		List<Transaction> parsedTransactions = new ArrayList<>();

		ParserCSV parserCSV = new ParserCSV();
		parserCSV.setFileName(args[0]);
		Runnable parserCSVTask = ()->{
			try {
				parsedTransactions.addAll(parserCSV.getListOfData());
				System.out.println("Thread parserCSVTask has been finished");
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		ParserJson parserJson = new ParserJson();
		parserJson.setFileName(args[1]);
		Runnable parserJsonTask = ()->{
			try {
				parsedTransactions.addAll(parserJson.getListOfData());
				System.out.println("Thread parserJsonTask has been finished");
			} catch (Exception e){
				e.printStackTrace();
			}
		};

		Runnable printParsedTransactions = ()->{
			for(Transaction transaction: parsedTransactions){
				System.out.println(transaction);
			}
		};

		parserCSVTask.run();
		parserJsonTask.run();
		printParsedTransactions.run();

	}

}
