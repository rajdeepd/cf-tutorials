package com.springsource.html5expense.redis.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.service.ExpenseTypeService;

@Service
public class ExpenseTypeRepository implements ExpenseTypeService{
	
	private static final String EXPENSETYPE = "EXPENSETYPE";
	
	@Autowired
	RedisTemplate<String, Cacheable> redisTemplate;
	
	public List<ExpenseType> getAllExpenseType(){
		List<ExpenseType> expenseTypeList = new ArrayList<ExpenseType>();
		for(Object key:redisTemplate.opsForHash().keys(EXPENSETYPE)){
			expenseTypeList.add((ExpenseType)redisTemplate.opsForHash().
					get(EXPENSETYPE, key));
		}return expenseTypeList;
	}
	
	public ExpenseType getExpenseTypeById(Long id){
		return (ExpenseType)redisTemplate.opsForHash().get(EXPENSETYPE,
				id);
	}
	
	public void save(ExpenseType type){
		List<ExpenseType> expenseTypeList = new ArrayList<ExpenseType>();
		expenseTypeList = getAllExpenseType();
		long id = 1;
		id = expenseTypeList.size();
		type.setId(id+1);
		redisTemplate.opsForHash().put(EXPENSETYPE, id+1, type);
	}
	
	

}
