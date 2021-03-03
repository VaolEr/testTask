package com.unlimint.testTask;

import com.unlimint.testTask.parsers.ParserCSV;
import com.unlimint.testTask.parsers.ParserJson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Currency;

@SpringBootApplication
public class TestTaskApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestTaskApplication.class, args);

		ParserCSV parserCSV = new ParserCSV();
		parserCSV.setFileName(args[0]);
		//parserCSV.readCsv();
		parserCSV.getListOfData();

		ParserJson parserJson = new ParserJson();
		parserJson.getListOfData();
	}

}
