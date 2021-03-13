package com.unlimint.testTask;

import com.unlimint.testTask.controller.ParserController;
import com.unlimint.testTask.model.Transaction;
import com.unlimint.testTask.parsers.Parser;
import com.unlimint.testTask.parsers.ParserCSV;
import com.unlimint.testTask.parsers.ParserJson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableAsync
public class TestTaskApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestTaskApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(TestTaskApplication.class);

		ParserController controller = context.getBean(ParserController.class);
		controller.getFileNamesFromArgs(args);

		List<Transaction> parsedTransactions = new ArrayList<>();

		//Runnable CSVParsing = ()->{
			//parsedTransactions.addAll(controller.runCSVParsing());
			try {
				parsedTransactions.addAll(controller.runCSVParsing().get());
				//controller.runCSVParsing().complete(null);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		//};

		//Runnable JsonParsing = ()->{
			//parsedTransactions.addAll(controller.runJsonParsing());
			try {
				parsedTransactions.addAll(controller.runJsonParsing().get());
				//controller.runJsonParsing().complete(null);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		//};

		Runnable printParsedTransactions = ()->{
			for(Transaction transaction: parsedTransactions){
				//Convert string to UTF-8 encoding
				ByteBuffer buffer = StandardCharsets.UTF_8.encode(transaction.toString());
				String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
				//Print result to console
				System.out.println(utf8EncodedString);
			}
		};

		//CSVParsing.run();
		//JsonParsing.run();
		printParsedTransactions.run();

		System.exit(0); //I add this line for faster termination off
		//If remove it app will be terminated by itself but with delay.
		//I understood, that it is happens because of @Async + CompletableFuture.Class
	}

}
