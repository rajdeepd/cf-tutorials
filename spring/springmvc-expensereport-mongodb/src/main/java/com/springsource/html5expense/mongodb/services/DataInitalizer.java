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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

@Service
public class DataInitalizer {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    private void init() {

        mongoTemplate.getCollection("ExpenseType").drop();
        mongoTemplate.getCollection("Expense").drop();
        DBCollection expenseTypeCollection = mongoTemplate.getCollection("ExpenseType");

        BasicDBObject expenseTypeDoc = new BasicDBObject();

        expenseTypeDoc.put("_id", new Long(1));
        expenseTypeDoc.put("expenseTypeId", new Long(1));
        expenseTypeDoc.put("name", "GYM");
        expenseTypeCollection.insert(expenseTypeDoc);

        expenseTypeDoc.clear();
        expenseTypeDoc.put("_id", new Long(2));
        expenseTypeDoc.put("expenseTypeId", new Long(2));
        expenseTypeDoc.put("name", "TELEPHONE");
        expenseTypeCollection.insert(expenseTypeDoc);

        expenseTypeDoc.clear();
        expenseTypeDoc.put("_id", new Long(3));
        expenseTypeDoc.put("expenseTypeId", new Long(3));
        expenseTypeDoc.put("name", "MEDICARE");
        expenseTypeCollection.insert(expenseTypeDoc);

        expenseTypeDoc.clear();
        expenseTypeDoc.put("_id", new Long(4));
        expenseTypeDoc.put("expenseTypeId", new Long(4));
        expenseTypeDoc.put("name", "TRAVEL");
        expenseTypeCollection.insert(expenseTypeDoc);
    }
}
