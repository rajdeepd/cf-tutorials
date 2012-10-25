package com.springsource.html5expense.mongodb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.service.ExpenseTypeService;

@Service
public class ExpenseTypeRepository implements ExpenseTypeService {


	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String EXPENSE_TYPE_COLLECTION = "ExpenseType";

	public List<ExpenseType> getAllExpenseType(){
		//mongoTemplate.find(new Query(ExpenseType.class), ExpenseType.class, EXPENSE_TYPE_COLLECTION);
		//mongoTemplate.
		logger.warn("getAllExpenseType");
		return mongoTemplate.findAll(ExpenseType.class,EXPENSE_TYPE_COLLECTION);
	}

	public ExpenseType getExpenseTypeById(Long id){
		List<ExpenseType> expenseTypeList = mongoTemplate.find(
				new Query(Criteria.where("expenseTypeId").is(id)), 
				ExpenseType.class,EXPENSE_TYPE_COLLECTION);
		logger.warn("getExpenseTypeById");
		return expenseTypeList!=null && expenseTypeList.size()>0?expenseTypeList.get(0):null;
	}

}
