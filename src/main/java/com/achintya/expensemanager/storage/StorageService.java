package com.achintya.expensemanager.storage;

import com.achintya.expensemanager.model.Expense;

import java.util.ArrayList;
import java.util.List;

public interface StorageService {
    public boolean save(Expense expense);
    public ArrayList<Expense> load();
}
