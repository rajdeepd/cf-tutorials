package com.springsource.html5expense.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EXPENSE")
public class Expense {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private State state = State.NEW;

    private Date expenseDate;
    
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "userId", nullable = false)
    @OneToOne
    private User user;

    @OneToOne
    private ExpenseType expenseType;

    public Expense() {

    }

    public Expense(String description, ExpenseType expenseType, Date expenseDate,
      Double amount, User user) {
        this.description = description;
        this.expenseType = expenseType;
        this.expenseDate = expenseDate;
        this.amount = amount;
        this.user = user;
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
}
