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

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Expense")
public class Expense implements Serializable{
	

	@Id
	private Long expenseId;

	private String description;
	
	private Double amount;
	
	private State state = State.NEW;
	
	private Date expenseDate;
	
	private User user;

	private ExpenseType expenseType;
	
	private Attachment attachment;
	
	public Expense(){
		
	}
	
	public Expense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment){
		this.description = description;
		this.expenseType = expenseType;
		this.expenseDate = expenseDate;
		this.amount = amount;
		this.user = user;
		this.attachment = attachment;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(Long id) {
		this.expenseId = id;
	}

	public ExpenseType getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(ExpenseType expenseType) {
		this.expenseType = expenseType;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
}
