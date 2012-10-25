/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.html5expense.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ExpenseReport")
public class ExpenseReport {
	
	@Id
	private Long expenseReportId;
	
	
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
