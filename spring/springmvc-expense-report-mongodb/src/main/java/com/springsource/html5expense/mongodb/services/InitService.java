package com.springsource.html5expense.mongodb.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

@Service
public class InitService {

	@Autowired
	MongoTemplate mongoTemplate;


	@PostConstruct
	private void init() {

		mongoTemplate.getCollection("ExpenseType").drop();
		mongoTemplate.getCollection("User").drop();
		mongoTemplate.getCollection("ExpenseType").drop();
		mongoTemplate.getCollection("Expense").drop();
		mongoTemplate.getCollection("Role").drop();
		DBCollection userCollection = mongoTemplate.getCollection("User");
		DBCollection roleCollection = mongoTemplate.getCollection("Role");
		DBCollection expenseTypeCollection = mongoTemplate.
				getCollection("ExpenseType");
		

		BasicDBObject roleDoc = new BasicDBObject();

		roleDoc.put("_id", new Long(1));
		roleDoc.put("roleId", new Long(1));
		roleDoc.put("roleName", "ROLE_USER");
		roleCollection.insert(roleDoc);
		
		roleDoc.clear();
		roleDoc.put("_id", new Long(1));
		roleDoc.put("roleId", new Long(1));
		roleDoc.put("roleName", "ROLE_MANAGER");
		roleCollection.insert(roleDoc);
		

		BasicDBObject userDoc = new BasicDBObject();
		userDoc.put("_id", new Long(1));
		userDoc.put("userId", new Long(1));
		userDoc.put("emailId", "admin@expensereport.com");
		userDoc.put("enabled", new Boolean(true));
		userDoc.put("userName","admin");
		userDoc.put("password", "admin");
		userDoc.put("role",roleDoc);
		
		userCollection.insert(userDoc);
		
		BasicDBObject expenseTypeDoc = new BasicDBObject();
		
		expenseTypeDoc.put("_id", new Long(1));
		expenseTypeDoc.put("expenseTypeId", new Long(1));
		expenseTypeDoc.put("name","GYM");
		expenseTypeCollection.insert(expenseTypeDoc);
		
		expenseTypeDoc.clear();
		expenseTypeDoc.put("_id", new Long(2));
		expenseTypeDoc.put("expenseTypeId", new Long(2));
		expenseTypeDoc.put("name","TELEPHONE");
		expenseTypeCollection.insert(expenseTypeDoc);
		
		expenseTypeDoc.clear();
		expenseTypeDoc.put("_id", new Long(3));
		expenseTypeDoc.put("expenseTypeId", new Long(3));;
		expenseTypeDoc.put("name","MEDICARE");
		expenseTypeCollection.insert(expenseTypeDoc);
		
		expenseTypeDoc.clear();
		expenseTypeDoc.put("_id", new Long(4));
		expenseTypeDoc.put("expenseTypeId", new Long(4));
		expenseTypeDoc.put("name","TRAVEL");
		expenseTypeCollection.insert(expenseTypeDoc);
	}
}
