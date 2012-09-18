package com.springsource.html5expense.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ExpenseReport {
	
	@Id
	@GeneratedValue
	private Long expenseReportId;
	
	
	@OneToOne
	private Expense expense;
	
	public  ExpenseReport(){
		
	}
	
	public Long getExpenseReportId() {
		return expenseReportId;
	}

	public void setExpenseReportId(Long expenseReportId) {
		this.expenseReportId = expenseReportId;
	}


	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public  ExpenseReport(Expense expense){
		this.expense = expense;
	}

}
