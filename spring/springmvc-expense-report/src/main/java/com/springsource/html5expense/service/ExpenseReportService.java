package com.springsource.html5expense.service;

import java.util.List;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseReport;

public interface ExpenseReportService {

	public Long createExpenseReport(Expense expense);
	
	public ExpenseReport getExpenseReportById(Long expensReportId);
	
	public List getAllExpenseReports();
}
