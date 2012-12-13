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

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.springsource.html5expense.model.ExpenseType;

@Service
public class DataInitalizer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    @Qualifier(KeyUtils.EXPENSE_TYPE_COUNT)
    private RedisAtomicLong expenseTypeCount;

    @PostConstruct
    private void init() {

        if (expenseTypeCount.intValue() > 0) {
            logger.debug("ExpenseTypes are already defined.");
            return;
        }

        logger.debug("Defining default ExpenseTypes");

        String[] expenseTypes = { "GYM", "TELEPHONE", "MEDICARE", "TRAVEL" };

        for (String expensType : expenseTypes) {
            long nextId = expenseTypeCount.incrementAndGet();
            ExpenseType type = new ExpenseType();
            type.setExpenseTypeId(nextId);
            type.setName(expensType);
            String expenseKey = KeyUtils.expenseTypeKey(nextId);
            redisTemplate.opsForValue().set(expenseKey, type);
        }
    }
}
