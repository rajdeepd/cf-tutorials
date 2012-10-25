package com.springsource.html5expense.mongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseReport;
import com.springsource.html5expense.service.ExpenseReportService;

@Service
public class ExpenseReportRepository implements ExpenseReportService {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public static String EXPENSEREPORT_COLLECTION = "ExpenseReport";

	public Long createExpenseReport(Expense expense){
		mongoTemplate.save(expense);
		return expense.getExpenseId();
	}
	
	public ExpenseReport getExpenseReportById(Long expenseReportId){
		List<ExpenseReport> expenseReportList = mongoTemplate.find(
				new Query(Criteria.where("expenseReportId").
						is(expenseReportId)), ExpenseReport.class,EXPENSEREPORT_COLLECTION);
		return expenseReportList!=null && expenseReportList.size()>0?expenseReportList.get(0):null;
	}
	
	public List<ExpenseReport> getAllExpenseReports(){
		return mongoTemplate.findAll(ExpenseReport.class,EXPENSEREPORT_COLLECTION);
	}

}
