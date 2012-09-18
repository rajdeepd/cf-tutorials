package com.springsource.html5expense.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "EXPENSE")
public class Expense implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;

	private String description;
	
	private Double amount;
	
	private State state = State.NEW;
	
	private Date expenseDate;
	
	@OneToOne
	private User user;

	@OneToOne
	private ExpenseType expenseType;
	
	@OneToOne
	@Cascade(CascadeType.DELETE_ORPHAN)
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
