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

import com.springsource.html5expense.model.ExpenseType;
import com.springsource.html5expense.services.ExpenseTypeService;

@Service
public class ExpenseTypeRepository implements ExpenseTypeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    @Qualifier(KeyUtils.EXPENSE_TYPE_COUNT)
    private RedisAtomicLong expenseTypeCount;

    @Override
    public List<ExpenseType> getAllExpenseType() {
        logger.debug("getAllExpenseType");
        List<ExpenseType> expenseTypeList = new ArrayList<ExpenseType>(
                expenseTypeCount.intValue());
        for (int i = 1; i <= expenseTypeCount.intValue(); i++) {
            ExpenseType expenseType = (ExpenseType) redisTemplate.opsForValue().get(
                    KeyUtils.expenseTypeKey(Long.valueOf(i)));
            if (expenseType != null) {
                expenseTypeList.add(expenseType);
            }
        }
        return expenseTypeList;
    }

    @Override
    public ExpenseType getExpenseTypeById(Long id) {
        logger.debug("getExpenseTypeById");
        return (ExpenseType) redisTemplate.opsForValue().get(KeyUtils.expenseTypeKey(id));
    }
}
