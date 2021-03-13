package com.unlimint.testTask.controller;

import com.unlimint.testTask.model.Transaction;
import com.unlimint.testTask.parsers.Parser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ParserController {

    //This class is realise the work of parsers.
    //I think my multithreading is awful.
    //In Spring for multithreading we have to use @Async,
    //but with this realisation this app finished with delay.

    private final Parser parserCSV;
    private final Parser parserJson;

    private String fileNameCSV;
    private String fileNameJson;

    //IoC with Qualifier
    //Because we have 2 beans and we are using one interface
    public ParserController(@Qualifier("parserCSV") Parser parserCSV,
                            @Qualifier("parserJson") Parser parserJson) {
        this.parserCSV = parserCSV;
        this.parserJson = parserJson;
    }

    public void getFileNamesFromArgs(String[] args){

        for (String arg : args) {
            String fileExtension = getFileExtension(new File(arg));
            if (fileExtension.equals("csv")) {
                this.fileNameCSV = arg;
            }
            if (fileExtension.equals("json")) {
                this.fileNameJson = arg;
            }
        }
    }

    @Async //for multithreading work
    public CompletableFuture<List<Transaction>> runCSVParsing(){
        assert this.fileNameCSV  != null;
        try {
            parserCSV.setFileName(this.fileNameCSV);
            //return CompletableFuture.completedFuture(parserCSV.getListOfData());
            return CompletableFuture.supplyAsync(() -> parserCSV.getListOfData());
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }

    @Async //for multithreading work
    public CompletableFuture<List<Transaction>> runJsonParsing(){
        assert this.fileNameJson  != null;
        try {
            parserJson.setFileName(this.fileNameJson);
            return CompletableFuture.supplyAsync(() -> parserJson.getListOfData());
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }

      //If you need more parsers, for example from XML,
//    @Async
//    public List<Transaction> runXMLParsing(){
//        //Write your code here
//        return null;
//    }

    //method for get file extension
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // if there is dot in file name and this dot is not the firs symbol in file name
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // then cut all symbols after last dot in file name, i.e. ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            //otherwise return "", i.e. extension not found
        else return "";
    }
}
