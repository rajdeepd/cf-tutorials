package com.springsource.html5expense.services;

import java.util.Date;
import java.util.List;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;

public interface ExpenseService {
    Long createExpense(String description, ExpenseType expenseType, Date expenseDate, Double amount);

    Expense getExpense(Long expenseId);

    List<Expense> getAllExpenses();

    List<Expense> getPendingExpensesList();

    Expense changeExpenseStatus(Long expenseId, String state);

    void deleteExpense(Long expenseId);

    void updateExpense(Expense expense);
}
