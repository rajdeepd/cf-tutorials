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
package com.springsource.html5expense.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.services.ExpenseService;

@Service
public class ExpenseRepository implements ExpenseService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static String EXPENSE_COLLECTION_NAME = "Expense";

    @Override
    public Long save(Expense expense) {
        List<Expense> expenseList = new ArrayList<Expense>();
        long val = 1;
        expenseList = getAllExpenses();
        val = expenseList.size();
        expense.setExpenseId(val + 1);
        mongoTemplate.save(expense, EXPENSE_COLLECTION_NAME);
        logger.debug("create new expense :" + expense.getExpenseId());
        return expense.getExpenseId();
    }

    @Override
    public List<Expense> getAllExpenses() {
        return mongoTemplate.findAll(Expense.class, EXPENSE_COLLECTION_NAME);
    }

    @Override
    public List<Expense> getPendingExpensesList() {
        List<State> stateList = new ArrayList<State>();
        stateList.add(State.NEW);
        stateList.add(State.OPEN);
        stateList.add(State.IN_REVIEW);
        return mongoTemplate.find(new Query(Criteria.where("state").in(stateList)),
                Expense.class, EXPENSE_COLLECTION_NAME);
    }

    @Override
    public Expense getExpense(Long expenseId) {
        List<Expense> expenseList = mongoTemplate.find(
                new Query(Criteria.where("expenseId").is(expenseId)), Expense.class);
        return expenseList != null && expenseList.size() > 0 ? expenseList.get(0) : null;
    }

    @Override
    public Expense changeExpenseStatus(Long expenseId, String state) {
        Update update = new Update();
        update.set("state", State.valueOf(state));
        Expense exp = getExpense(expenseId);
        logger.debug("Get Expense " + exp.getDescription());
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(expenseId)), update,
                EXPENSE_COLLECTION_NAME);
        return getExpense(expenseId);
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = getExpense(expenseId);
        mongoTemplate.remove(expense);
    }

    @Override
    public void updateExpense(Long expenseId, Update update) {
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(expenseId)), update,
                EXPENSE_COLLECTION_NAME);
    }
}
