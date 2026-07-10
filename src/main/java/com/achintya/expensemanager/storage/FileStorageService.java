package com.achintya.expensemanager.storage;

import com.achintya.expensemanager.model.Expense;

import java.beans.JavaBean;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService implements StorageService{
    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);
    @Override
    public boolean save(Expense expense) {
        try(FileWriter fileWriter= new FileWriter("data.txt",true)){
            fileWriter.write(expense.toCSV());
            fileWriter.write("\n");
            logger.info("data saved to file successfully");
            return true;
        }
        catch(IOException e){
            logger.info("exception occurred!Please retry");
            return false;
        }
    }

    @Override
    public ArrayList<Expense> load() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("data.txt"))){
            String currentLine;
            while((currentLine = reader.readLine())!=null) {
                String[] currentRow=currentLine.split(",");
                int expenseId = Integer.parseInt(currentRow[0]);
                float expenseAmount = Float.parseFloat(currentRow[1]);
                String category = currentRow[2];
                String description = currentRow[3];
                String date = currentRow[4];
                Expense e = new Expense(expenseId,expenseAmount,category,description,date);
                expenses.add(e);
            }
            return expenses;
        }
        catch(FileNotFoundException e){
            logger.info("unable to find the file!please check path");
            //return null;

        }
        catch (IOException e) {
            logger.info("exception occured");
            //return null;
        }
        return expenses;
    }
}
