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
package com.springsource.html5expense.redis.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.Expense;
import com.springsource.html5expense.model.State;
import com.springsource.html5expense.services.ExpenseService;

@Service
public class ExpenseRepository implements ExpenseService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    @Qualifier(KeyUtils.EXPENSE_COUNT)
    private RedisAtomicLong expenseCount;

    @Override
    public Long save(Expense expense) {
        Long expenseId = expenseCount.incrementAndGet();
        expense.setExpenseId(expenseId);
        redisTemplate.opsForValue().set(KeyUtils.expenseKey(expenseId), expense);
        logger.debug("create new expense :" + expense.getExpenseId());
        return expense.getExpenseId();
    }

    @Override
    public List<Expense> getAllExpenses() {
        List<Expense> expenseList = new ArrayList<Expense>(expenseCount.intValue());
        for (int i = 1; i <= expenseCount.intValue(); i++) {
            Expense expense = (Expense) redisTemplate.opsForValue().get(
                    KeyUtils.expenseKey(Long.valueOf(i)));
            if (expense != null) {
                expenseList.add(expense);
            }
        }
        return expenseList;
    }

    @Override
    public List<Expense> getPendingExpensesList() {
        List<State> stateList = new ArrayList<State>();
        stateList.add(State.NEW);
        stateList.add(State.OPEN);
        stateList.add(State.IN_REVIEW);
        List<Expense> expenseList = getAllExpenses();
        List<Expense> pendingList = new ArrayList<Expense>();
        for (Expense expense : expenseList) {
            if (stateList.contains(expense.getState())) {
                pendingList.add(expense);
            }
        }
        return pendingList;
    }

    @Override
    public Expense getExpense(Long expenseId) {
        return (Expense) redisTemplate.opsForValue().get(KeyUtils.expenseKey(expenseId));
    }

    @Override
    public Expense changeExpenseStatus(Long expenseId, String state) {
        Expense expense = this.getExpense(expenseId);
        expense.setState(State.valueOf(state));
        this.updateExpense(expense);
        return expense;
    }

    @Override
    public void deleteExpense(Long expenseId) {
        redisTemplate.delete(KeyUtils.expenseKey(expenseId));
    }

    @Override
    public void updateExpense(Expense expense) {
        redisTemplate.opsForValue().set(KeyUtils.expenseKey(expense.getExpenseId()),
                expense);
    }
}
