package com.springsource.html5expense.mongodb.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseService;

@Service
public class ExpenseRepository implements ExpenseService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static String EXPENSE_COLLECTION_NAME = "Expense";
	
	public Long createExpense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment) {
		// TODO Auto-generated method stub
		Expense expense = new Expense(description,expenseType,expenseDate,amount,user,attachment);
		List<Expense> expenseList = new ArrayList<Expense>();
		long val = 1;
		expenseList = getAllExpenses();
		val = expenseList.size();
		expense.setExpenseId(val+1);
		mongoTemplate.save(expense,EXPENSE_COLLECTION_NAME);
		logger.warn("create new expense :"+expense.getExpenseId());
		return expense.getExpenseId();
	}
	
	public List<Expense> getAllExpenses(){
		return mongoTemplate.findAll(Expense.class,EXPENSE_COLLECTION_NAME);
	}
	
	public List<Expense> getExpensesByUser(User user){
		return mongoTemplate.find(new Query(Criteria.where("user").is(user)) , Expense.class,EXPENSE_COLLECTION_NAME);
	}
	
	public List<Expense> getPendingExpensesList(){
		List<State> stateList = new ArrayList<State>();
		stateList.add(State.NEW);
		stateList.add(State.OPEN);
		stateList.add(State.IN_REVIEW);
		return mongoTemplate.find(new Query(Criteria.where("state").in(stateList)), Expense.class, EXPENSE_COLLECTION_NAME);
	}
	
	public List<Expense> getApprovedAndRejectedExpensesList(){
		List<State> stateList = new ArrayList<State>();
		stateList.add(State.APPROVED);
		stateList.add(State.REJECTED);
		return mongoTemplate.find(new Query(Criteria.where("state").in(stateList)), Expense.class,EXPENSE_COLLECTION_NAME);
	}
	
	public Expense getExpense(Long expenseId){
		//List<Expense> expenseList = mongoTemplate.find(new Query(Criteria.where("expenseId").is(expenseId)),Expense.class,EXPENSE_COLLECTION_NAME);
		List<Expense> expenseList = mongoTemplate.find(new Query(Criteria.where("expenseId").is(expenseId)),Expense.class);
		return expenseList!=null && expenseList.size()>0?expenseList.get(0):null;
	}
	
	public Expense changeExpenseStatus(Long expenseId,String state){
		Update update = new Update();
		update.set("state", State.valueOf(state));
		Expense exp = getExpense(expenseId);
		logger.warn("Get Expense "+exp.getDescription());
		mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(expenseId)), update,EXPENSE_COLLECTION_NAME);
		return getExpense(expenseId);
	}
	
	
	
	public void deleteExpense(Long expenseId){
		Expense expense = getExpense(expenseId);
		mongoTemplate.remove(expense);
	}
	
	public void updateExpense(Long expenseId,String description,Double amount,ExpenseType expenseType){
		Update update = new Update();
		update.set("description", description);
		update.set("amount",amount);
		update.set("expenseType",expenseType);
		mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(expenseId)), update,EXPENSE_COLLECTION_NAME);
		
	}

}
