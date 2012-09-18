package com.springsource.html5expense.service;

import java.util.List;

import com.springsource.html5expense.model.ExpenseType;

public interface ExpenseTypeService {

	public List<ExpenseType> getAllExpenseType();
	
	public ExpenseType getExpenseTypeById(Long id);
}
