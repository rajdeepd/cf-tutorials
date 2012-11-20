package com.springsource.html5expense.services;

import java.util.List;
import com.springsource.html5expense.model.ExpenseType;

public interface ExpenseTypeService {    
	List<ExpenseType> getAllExpenseType();
    ExpenseType getExpenseTypeById(Long id);
}
