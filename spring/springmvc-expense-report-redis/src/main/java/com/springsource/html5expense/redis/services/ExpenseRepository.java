package com.springsource.html5expense.redis.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Attachment;
import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseService;

@Service
public class ExpenseRepository implements ExpenseService{
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	
	private final Logger logger = Logger.getLogger(ExpenseRepository.class);
	
	private static final String EXPENSE = "EXPENSE";
	
	public Long createExpense(String description,ExpenseType expenseType,Date expenseDate,
			Double amount,User user,Attachment attachment){
		Expense expense = new Expense(description,expenseType,expenseDate,amount,
				user,attachment);
		long id = 1;
		List<Expense> expenseList = new ArrayList<Expense>();
		expenseList = getAllExpenses();
		id = expenseList.size();
		expense.setId(id+1);
		redisTemplate.opsForHash().put(EXPENSE, id, expense);
		logger.info("create expense expense id "+(id+1));
		return expense.getId();
		
	}
	
	public Expense getExpense(Long expenseId){
		return (Expense)redisTemplate.opsForHash().get(EXPENSE, expenseId);
	}
	
	public List<Expense> getAllExpenses(){
		Set<Object> keys = redisTemplate.opsForHash().keys(EXPENSE);
		List<Expense> expenseList = new ArrayList<Expense>();
		for(Object key:keys){
			expenseList.add((Expense)redisTemplate.opsForHash().get(EXPENSE, key));
			logger.info("get all expense expense key "+key);
		}
		return expenseList;
	}
	
	public List<Expense> getExpensesByUser(User user){
		Set<Object> keys = redisTemplate.opsForHash().keys(EXPENSE);
		List<Expense> expenseList = new ArrayList<Expense>();
		for(Object key:keys){
			Expense exp = (Expense)redisTemplate.opsForHash().get(EXPENSE, key);
			if(exp!=null && exp.getUser().getUserId().equals(user.getUserId())){
				expenseList.add((Expense)redisTemplate.opsForHash().get(EXPENSE, key));
				logger.info("get expense by user expense key "+key);
			}
		}return expenseList;
	}
	
	public List<Expense> getPendingExpensesList(){
		Set<Object> keys = redisTemplate.opsForHash().keys(EXPENSE);
		List<Expense> expenseList = new ArrayList<Expense>();
		for(Object key:keys){
			Expense exp = (Expense)redisTemplate.opsForHash().get(EXPENSE, key);
			if(exp!=null && !exp.getState().equals(State.APPROVED)
					&& !exp.getState().equals(State.REJECTED)){
				expenseList.add((Expense)redisTemplate.opsForHash().get(EXPENSE, key));
			}
		}return expenseList;
	}
	
	public List<Expense> getApprovedAndRejectedExpensesList(){
		Set<Object> keys = redisTemplate.opsForHash().keys(EXPENSE);
		List<Expense> expenseList = new ArrayList<Expense>();
		for(Object key:keys){
			Expense exp = (Expense)redisTemplate.opsForHash().get(EXPENSE, key);
			if(exp!=null && exp.getState().equals(State.APPROVED)
					&& exp.getState().equals(State.REJECTED)){
				expenseList.add((Expense)redisTemplate.opsForHash().get(EXPENSE, key));
			}
		}return expenseList;
	}
	
	public Expense changeExpenseStatus(Long expenseId,String state){
		Expense expense = getExpense(expenseId);
		expense.setState(State.valueOf(state));
		redisTemplate.opsForHash().put(EXPENSE, expenseId, expense);
		return expense;
		
	}
	
	public void deleteExpense(Long expenseId){
		Expense expense = getExpense(expenseId);
		redisTemplate.opsForHash().delete(EXPENSE, expense);
	}
	
	public void updateExpense(Long expenseId,String description,Double amount,ExpenseType expenseType){
		Expense expense = getExpense(expenseId);
		expense.setAmount(amount);
		expense.setDescription(description);
		expense.setExpenseType(expenseType);
		redisTemplate.opsForHash().put(EXPENSE, expenseId, expense);
	}

	

}
