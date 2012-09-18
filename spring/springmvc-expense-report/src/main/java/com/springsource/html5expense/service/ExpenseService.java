package com.springsource.html5expense.service;

import java.util.Date;
import java.util.List;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.User;

public interface ExpenseService {

	public Long createExpense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment);
	
	public Expense getExpense(Long expenseId);
	
	public List getAllExpenses();
	
	public List<Expense> getExpensesByUser(User user);
	
	public List<Expense> getPendingExpensesList();
	
	public List<Expense> getApprovedAndRejectedExpensesList();
	
	public Expense changeExpenseStatus(Long expenseId,String state);
	
	public void deleteExpense(Long expenseId);
	
	public void updateExpense(Long expenseId,String description,Double amount,ExpenseType expenseType);
}
