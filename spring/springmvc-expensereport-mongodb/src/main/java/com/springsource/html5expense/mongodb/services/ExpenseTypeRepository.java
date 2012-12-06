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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.services.ExpenseTypeService;

@Service
public class ExpenseTypeRepository implements ExpenseTypeService {

    @Autowired
    MongoTemplate mongoTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public static final String EXPENSE_TYPE_COLLECTION = "ExpenseType";

    @Override
    public List<ExpenseType> getAllExpenseType() {
        logger.debug("getAllExpenseType");
        return mongoTemplate.findAll(ExpenseType.class, EXPENSE_TYPE_COLLECTION);
    }

    @Override
    public ExpenseType getExpenseTypeById(Long id) {
        List<ExpenseType> expenseTypeList = mongoTemplate.find(
                new Query(Criteria.where("expenseTypeId").is(id)), ExpenseType.class,
                EXPENSE_TYPE_COLLECTION);
        logger.debug("getExpenseTypeById");
        return expenseTypeList != null && expenseTypeList.size() > 0 ? expenseTypeList.get(0)
                : null;
    }

}
