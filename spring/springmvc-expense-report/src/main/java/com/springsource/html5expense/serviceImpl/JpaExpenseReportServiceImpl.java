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
package com.springsource.html5expense.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.ExpenseReport;
import com.springsource.html5expense.model.User;
import com.springsource.html5expense.service.ExpenseReportService;

public class JpaExpenseReportServiceImpl implements ExpenseReportService {

private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	@Transactional
	public Long createExpenseReport(Expense expense){
		ExpenseReport expenseReport = new ExpenseReport(expense);
		getEntityManager().persist(expenseReport);
		return expenseReport.getExpenseReportId();
	}
	
	@Override
	@Transactional
	public ExpenseReport getExpenseReportById(Long expenseReportId){
		ExpenseReport expenseReport = new ExpenseReport();
		expenseReport.setExpenseReportId(new Long(expenseReportId));
		expenseReport = getEntityManager().find(ExpenseReport.class, expenseReportId);
		return expenseReport;
	}
	
	@Override
	@Transactional
	public List getAllExpenseReports(){
		Query query = getEntityManager().createQuery("select e from expensereport e");
		return query.getResultList();
	}
}
